package com.example.submission1jetpack.ui.movies

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val moviesEntities = viewModel.getMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }
}