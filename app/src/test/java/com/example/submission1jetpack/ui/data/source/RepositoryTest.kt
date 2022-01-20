package com.example.submission1jetpack.ui.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.submission1jetpack.data.source.remote.RemoteDataSource
import com.example.submission1jetpack.utils.DataDummy
import com.example.submission1jetpack.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val repository = FakeRepository(remote)

    private val listMovieResponse = DataDummy.generateRemoteDummyMovies()
    private val movieId = listMovieResponse[0].id
    private val listTvshowResponse = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = listTvshowResponse[0].id
    private val movieResponse = DataDummy.generateRemoteDummyMovies()[0]
    private val tvShowResponse = DataDummy.generateRemoteDummyTvShows()[0]

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(listMovieResponse)
            null
        }.`when`(remote).getAllMovies(any())

        val moviesEntities = LiveDataTestUtil.getValue(repository.getAllMovies())

        verify(remote).getAllMovies(any())

        assertNotNull(moviesEntities)
        assertEquals(listMovieResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(listTvshowResponse)
            null
        }.`when`(remote).getAllTvShows(any())

        val tvshowsEntities = LiveDataTestUtil.getValue(repository.getAllTvShows())

        verify(remote).getAllTvShows(any())

        assertNotNull(tvshowsEntities)
        assertEquals(listTvshowResponse.size.toLong(), tvshowsEntities.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMoviesDetailCallback)
                .onMoviesDetailReceived(movieResponse)
        }.`when`(remote).getDetailMovies(eq(movieId.toString()), any())

        val movieEntities =
            LiveDataTestUtil.getValue(repository.getDetailMovies(movieId.toString()))

        verify(remote).getDetailMovies(eq(movieId.toString()), any())

        assertNotNull(movieEntities)
        assertNotNull(movieEntities.movieTitle)
        assertEquals(movieResponse.title, movieEntities.movieTitle)
    }

    @Test
    fun getDetailTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowsDetailCallback)
                .onTvShowsDetailReceived(tvShowResponse)
        }.`when`(remote).getDetailTvShows(eq(tvShowId.toString()), any())

        val tvshowEntities =
            LiveDataTestUtil.getValue(repository.getDetailTvShows(tvShowId.toString()))

        verify(remote).getDetailTvShows(eq(tvShowId.toString()), any())

        assertNotNull(tvshowEntities)
        assertNotNull(tvshowEntities.tvShowTitle)
        assertEquals(tvShowResponse.name, tvshowEntities.tvShowTitle)
    }

}