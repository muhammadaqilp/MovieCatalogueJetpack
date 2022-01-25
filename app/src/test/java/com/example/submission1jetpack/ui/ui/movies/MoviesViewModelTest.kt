package com.example.submission1jetpack.ui.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.utils.SortUtils
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

@RunWith(MockitoJUnitRunner.Silent::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel
    private val sort = SortUtils.NEWEST

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getMoviesNotNull() {
        val dummyMovies = Resource.success(pagedList)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getAllMovies(sort)).thenReturn(movies)
        val movieEntities = viewModel.getMovies(sort).value?.data
        verify(repository).getAllMovies(sort)

        assertNotNull(movieEntities)

        viewModel.getMovies(sort).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getMoviesSizeEqualsTen() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(repository.getAllMovies(sort)).thenReturn(movies)
        val movieEntities = viewModel.getMovies(sort).value?.data
        verify(repository).getAllMovies(sort)

        assertEquals(10, movieEntities?.size)

        viewModel.getMovies(sort).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}