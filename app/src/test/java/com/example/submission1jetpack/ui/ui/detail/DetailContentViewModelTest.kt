package com.example.submission1jetpack.ui.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.utils.DataDummy
import com.example.submission1jetpack.vo.Resource
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
    private lateinit var m: MovieEntity

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvshowObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailContentViewModel(repository)
        m = MovieEntity(
            dummyMovie.movieId,
            dummyMovie.movieTitle,
            dummyMovie.movieOverview,
            dummyMovie.movieRelease,
            dummyMovie.movieDuration,
            dummyMovie.moviePoster,
            dummyMovie.isFavorite
        )
        viewModel.setSelectedMovieId(movieId)
        viewModel.setSelectedTvShowId(tvShowId)
    }

    @Test
    fun getMoviesHasSameValue() {
        val dummy = Resource.success(dummyMovie)
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = dummy

        viewModel.setSelectedMovieId(movieId)
        `when`(repository.getDetailMovies(movieId)).thenReturn(movies)

        viewModel.movieList.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummy)

        verify(repository).getDetailMovies(movieId)

        val movieEntity = viewModel.movieList.value?.data

        assertEquals(dummyMovie.movieId, movieEntity?.movieId)
        assertEquals(dummyMovie.movieTitle, movieEntity?.movieTitle)
        assertEquals(dummyMovie.movieDuration, movieEntity?.movieDuration)
        assertEquals(dummyMovie.movieRelease, movieEntity?.movieRelease)
        assertEquals(dummyMovie.movieOverview, movieEntity?.movieOverview)
        assertEquals(dummyMovie.moviePoster, movieEntity?.moviePoster)
        assertEquals(dummyMovie.isFavorite, movieEntity?.isFavorite)
    }

    @Test
    fun getMoviesNotNull() {
        val dummy = Resource.success(dummyMovie)
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = dummy

        viewModel.setSelectedMovieId(movieId)
        `when`(repository.getDetailMovies(movieId)).thenReturn(movies)

        viewModel.movieList.observeForever(movieObserver)
        verify(movieObserver).onChanged(dummy)

        verify(repository).getDetailMovies(movieId)

        val movieEntity = viewModel.movieList.value?.data

        assertNotNull(movieEntity)
        assertNotNull(movieEntity?.movieId)
        assertNotNull(movieEntity?.movieTitle)
        assertNotNull(movieEntity?.movieDuration)
        assertNotNull(movieEntity?.movieRelease)
        assertNotNull(movieEntity?.movieOverview)
        assertNotNull(movieEntity?.moviePoster)
    }

    @Test
    fun getTvShowsHasSameValue() {
        val dummy = Resource.success(dummyTvShow)
        val tv = MutableLiveData<Resource<TvShowEntity>>()
        tv.value = dummy

        viewModel.setSelectedTvShowId(tvShowId)
        `when`(repository.getDetailTvShows(tvShowId)).thenReturn(tv)

        viewModel.tvshowList.observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummy)

        verify(repository).getDetailTvShows(tvShowId)

        val tvshowEntity = viewModel.tvshowList.value?.data

        assertEquals(dummyTvShow.tvShowId, tvshowEntity?.tvShowId)
        assertEquals(dummyTvShow.tvShowTitle, tvshowEntity?.tvShowTitle)
        assertEquals(dummyTvShow.tvShowDuration, tvshowEntity?.tvShowDuration)
        assertEquals(dummyTvShow.tvShowRelease, tvshowEntity?.tvShowRelease)
        assertEquals(dummyTvShow.tvShowOverview, tvshowEntity?.tvShowOverview)
        assertEquals(dummyTvShow.tvShowPoster, tvshowEntity?.tvShowPoster)
    }

    @Test
    fun getTvShowsNotNull() {
        val dummy = Resource.success(dummyTvShow)
        val tv = MutableLiveData<Resource<TvShowEntity>>()
        tv.value = dummy

        viewModel.setSelectedTvShowId(tvShowId)
        `when`(repository.getDetailTvShows(tvShowId)).thenReturn(tv)

        viewModel.tvshowList.observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummy)

        verify(repository).getDetailTvShows(tvShowId)

        val tvshowEntity = viewModel.tvshowList.value?.data

        assertNotNull(tvshowEntity)
        assertNotNull(tvshowEntity?.tvShowId)
        assertNotNull(tvshowEntity?.tvShowTitle)
        assertNotNull(tvshowEntity?.tvShowDuration)
        assertNotNull(tvshowEntity?.tvShowRelease)
        assertNotNull(tvshowEntity?.tvShowOverview)
        assertNotNull(tvshowEntity?.tvShowPoster)
    }
}