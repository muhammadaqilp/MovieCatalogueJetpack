package com.example.submission1jetpack.ui.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailContentViewModelTest {

    private lateinit var viewModel: DetailContentViewModel
    private val dummyMovie = DataDummy.generateMovieData()[0]
    private val movieId = dummyMovie.movieId
    private val dummyTvShow = DataDummy.generateTvShowsData()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvshowObserver: Observer<TvShowEntity>

    @Before
    fun setSelectedId() {
        viewModel = DetailContentViewModel(repository)
        viewModel.setSelectedMovieId(movieId.toString())
        viewModel.setSelectedTvShowId(tvShowId.toString())
    }

    @Test
    fun getMoviesHasSameValue() {
        val movies = MutableLiveData<MovieEntity>()
        movies.value = dummyMovie

        `when`(repository.getDetailMovies(movieId.toString())).thenReturn(movies)
        viewModel.setSelectedMovieId(dummyMovie.movieId.toString())
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(repository).getDetailMovies(movieId.toString())

        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.movieTitle, movieEntity.movieTitle)
        assertEquals(dummyMovie.movieDuration, movieEntity.movieDuration)
        assertEquals(dummyMovie.movieRelease, movieEntity.movieRelease)
        assertEquals(dummyMovie.movieOverview, movieEntity.movieOverview)
        assertEquals(dummyMovie.moviePoster, movieEntity.moviePoster)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getMoviesNotNull() {
        val movies = MutableLiveData<MovieEntity>()
        movies.value = dummyMovie

        `when`(repository.getDetailMovies(movieId.toString())).thenReturn(movies)
        viewModel.setSelectedMovieId(dummyMovie.movieId.toString())
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(repository).getDetailMovies(movieId.toString())

        assertNotNull(movieEntity)
        assertNotNull(movieEntity.movieId)
        assertNotNull(movieEntity.movieTitle)
        assertNotNull(movieEntity.movieDuration)
        assertNotNull(movieEntity.movieRelease)
        assertNotNull(movieEntity.movieOverview)
        assertNotNull(movieEntity.moviePoster)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowsHasSameValue() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        `when`(repository.getDetailTvShows(tvShowId.toString())).thenReturn(tvShow)
        viewModel.setSelectedTvShowId(dummyTvShow.tvShowId.toString())
        val tvshowEntity = viewModel.getTvShows().value as TvShowEntity
        verify(repository).getDetailTvShows(tvShowId.toString())

        assertEquals(dummyTvShow.tvShowId, tvshowEntity.tvShowId)
        assertEquals(dummyTvShow.tvShowTitle, tvshowEntity.tvShowTitle)
        assertEquals(dummyTvShow.tvShowDuration, tvshowEntity.tvShowDuration)
        assertEquals(dummyTvShow.tvShowRelease, tvshowEntity.tvShowRelease)
        assertEquals(dummyTvShow.tvShowOverview, tvshowEntity.tvShowOverview)
        assertEquals(dummyTvShow.tvShowPoster, tvshowEntity.tvShowPoster)

        viewModel.getTvShows().observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun getTvShowsNotNull() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShow

        `when`(repository.getDetailTvShows(tvShowId.toString())).thenReturn(tvShow)
        viewModel.setSelectedTvShowId(dummyTvShow.tvShowId.toString())
        val tvshowEntity = viewModel.getTvShows().value as TvShowEntity
        verify(repository).getDetailTvShows(tvShowId.toString())

        assertNotNull(tvshowEntity)
        assertNotNull(tvshowEntity.tvShowId)
        assertNotNull(tvshowEntity.tvShowTitle)
        assertNotNull(tvshowEntity.tvShowDuration)
        assertNotNull(tvshowEntity.tvShowRelease)
        assertNotNull(tvshowEntity.tvShowOverview)
        assertNotNull(tvshowEntity.tvShowPoster)

        viewModel.getTvShows().observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummyTvShow)
    }
}