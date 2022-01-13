package com.example.submission1jetpack.ui.detail

import com.example.submission1jetpack.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class DetailContentViewModelTest {

    private lateinit var viewModel: DetailContentViewModel
    private val dummyMovie = DataDummy.generateMovieData()[0]
    private val movieId = dummyMovie.movieId
    private val dummyTvShow = DataDummy.generateTvShowsData()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @Before
    fun setSelectedId() {
        viewModel = DetailContentViewModel()
        viewModel.setSelectedMovieId(movieId)
        viewModel.setSelectedTvShowId(tvShowId)
    }

    @Test
    fun getMovies() {
        viewModel.setSelectedMovieId(dummyMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.movieTitle, movieEntity.movieTitle)
        assertEquals(dummyMovie.movieDuration, movieEntity.movieDuration)
        assertEquals(dummyMovie.movieRelease, movieEntity.movieRelease)
        assertEquals(dummyMovie.movieOverview, movieEntity.movieOverview)
        assertEquals(dummyMovie.moviePoster, movieEntity.moviePoster)
    }

    @Test
    fun getTvShows() {
        viewModel.setSelectedMovieId(dummyTvShow.tvShowId)
        val tvShowEntity = viewModel.getTvShows()
        assertNotNull(dummyTvShow)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.tvShowTitle, tvShowEntity.tvShowTitle)
        assertEquals(dummyTvShow.tvShowDuration, tvShowEntity.tvShowDuration)
        assertEquals(dummyTvShow.tvShowRelease, tvShowEntity.tvShowRelease)
        assertEquals(dummyTvShow.tvShowOverview, tvShowEntity.tvShowOverview)
        assertEquals(dummyTvShow.tvShowPoster, tvShowEntity.tvShowPoster)
    }
}