package com.example.submission1jetpack.ui.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submission1jetpack.data.MovieEntity
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
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getMoviesNotNull() {
        val dummyMovies = DataDummy.generateMovieData()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(repository.getAllMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies().value
        verify(repository).getAllMovies()

        assertNotNull(moviesEntities)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getMoviesSizeEqualsTen() {
        val dummyMovies = DataDummy.generateMovieData()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(repository.getAllMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies().value
        verify(repository).getAllMovies()

        assertEquals(10, moviesEntities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}