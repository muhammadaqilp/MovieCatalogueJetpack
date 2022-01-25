package com.example.submission1jetpack.ui.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
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
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListTv: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getAllFavoriteMoviesNotNull() {
        val dummyMovies = pagedList
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovies

        `when`(repository.getFavoriteMovies()).thenReturn(movie)
        val movieEntitites = viewModel.getFavoriteMovies().value
        verify(repository).getFavoriteMovies()

        assertNotNull(movieEntitites)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getAllFavoriteMoviesEqualsTen() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovies

        `when`(repository.getFavoriteMovies()).thenReturn(movie)
        val movieEntities = viewModel.getFavoriteMovies().value
        verify(repository).getFavoriteMovies()

        assertEquals(5, movieEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getAllFavoriteTvShowNotNull() {
        val dummyTv = pagedListTv
        val tv = MutableLiveData<PagedList<TvShowEntity>>()
        tv.value = dummyTv

        `when`(repository.getFavoriteTvShows()).thenReturn(tv)
        val tvEntities = viewModel.getFavoriteTvShow().value
        verify(repository).getFavoriteTvShows()

        assertNotNull(tvEntities)

        viewModel.getFavoriteTvShow().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }

    @Test
    fun getAllFavoriteTvShowEqualsTen() {
        val dummyTv = pagedListTv
        `when`(dummyTv.size).thenReturn(10)
        val tv = MutableLiveData<PagedList<TvShowEntity>>()
        tv.value = dummyTv

        `when`(repository.getFavoriteTvShows()).thenReturn(tv)
        val tvEntities = viewModel.getFavoriteTvShow().value
        verify(repository).getFavoriteTvShows()

        assertEquals(10, tvEntities?.size)

        viewModel.getFavoriteTvShow().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }

}