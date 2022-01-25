package com.example.submission1jetpack.ui.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.submission1jetpack.data.source.local.LocalDataSource
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.data.source.remote.RemoteDataSource
import com.example.submission1jetpack.ui.utils.PagedListUtils
import com.example.submission1jetpack.utils.AppExecutors
import com.example.submission1jetpack.utils.DataDummy
import com.example.submission1jetpack.utils.LiveDataTestUtil
import com.example.submission1jetpack.utils.SortUtils
import com.example.submission1jetpack.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val repository = FakeRepository(remote, local, appExecutors)

    private val listMovieResponse = DataDummy.generateMovieData()
    private val movieId = listMovieResponse[0].movieId
    private val listTvshowResponse = DataDummy.generateTvShowsData()
    private val tvShowId = listTvshowResponse[0].tvShowId
    private val movieResponse = DataDummy.generateMovieData()[0]
    private val tvShowResponse = DataDummy.generateTvShowsData()[0]
    private val sort = SortUtils.NEWEST

    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies(sort)).thenReturn(dataSourceFactory)
        repository.getAllMovies(sort)

        val moviesEntities = Resource.success(PagedListUtils.mockPagedList(listMovieResponse))

        verify(local).getAllMovies(sort)

        assertNotNull(moviesEntities.data)
        assertEquals(listMovieResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows(sort)).thenReturn(dataSourceFactory)
        repository.getAllTvShows(sort)

        val tvshowsEntities = Resource.success(PagedListUtils.mockPagedList(listTvshowResponse))

        verify(local).getAllTvShows(sort)

        assertNotNull(tvshowsEntities.data)
        assertEquals(listTvshowResponse.size.toLong(), tvshowsEntities.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllFavoriteMovies()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovies()

        val moviesEntities = Resource.success(PagedListUtils.mockPagedList(listMovieResponse))

        verify(local).getAllFavoriteMovies()

        assertNotNull(moviesEntities.data)
        assertEquals(listMovieResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getAllFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllFavoriteTvShows()).thenReturn(dataSourceFactory)
        repository.getFavoriteTvShows()

        val tvshowsEntities = Resource.success(PagedListUtils.mockPagedList(listTvshowResponse))

        verify(local).getAllFavoriteTvShows()

        assertNotNull(tvshowsEntities.data)
        assertEquals(listTvshowResponse.size.toLong(), tvshowsEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = movieResponse
        `when`(local.getDetailMovies(movieId.toInt())).thenReturn(dummyEntity)

        val movieEntities =
            LiveDataTestUtil.getValue(repository.getDetailMovies(movieId))

        verify(local).getDetailMovies(movieId.toInt())

        assertNotNull(movieEntities.data)
        assertNotNull(movieEntities.data?.movieTitle)
        assertEquals(movieResponse.movieTitle, movieEntities.data?.movieTitle)
    }

    @Test
    fun getDetailTvShows() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = tvShowResponse
        `when`(local.getDetailTvShows(tvShowId.toInt())).thenReturn(dummyEntity)

        val tvShowEntities =
            LiveDataTestUtil.getValue(repository.getDetailTvShows(tvShowId))

        verify(local).getDetailTvShows(tvShowId.toInt())

        assertNotNull(tvShowEntities.data)
        assertNotNull(tvShowEntities.data?.tvShowTitle)
        assertEquals(tvShowResponse.tvShowTitle, tvShowEntities.data?.tvShowTitle)
    }

}