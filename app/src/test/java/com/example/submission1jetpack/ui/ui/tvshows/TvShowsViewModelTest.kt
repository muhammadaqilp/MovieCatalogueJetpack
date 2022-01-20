package com.example.submission1jetpack.ui.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setup() {
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun getTvShowsNotNull() {
        val dummyTvshow = DataDummy.generateTvShowsData()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvshow

        `when`(repository.getAllTvShows()).thenReturn(tvShow)
        val tvshowEntities = viewModel.getTvShows().value
        verify(repository).getAllTvShows()

        assertNotNull(tvshowEntities)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvshow)
    }

    @Test
    fun getTvShowSizeEqualsTen() {
        val dummyTvshow = DataDummy.generateTvShowsData()
        val tvShow = MutableLiveData<List<TvShowEntity>>()
        tvShow.value = dummyTvshow

        `when`(repository.getAllTvShows()).thenReturn(tvShow)
        val tvshowEntities = viewModel.getTvShows().value
        verify(repository).getAllTvShows()

        assertEquals(10, tvshowEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvshow)
    }
}