package com.example.submission1jetpack.ui.tvshows

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setup() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowsEntities = viewModel.getTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(10, tvShowsEntities.size)
    }
}