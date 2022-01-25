package com.example.submission1jetpack.ui.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
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
class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel
    private val sort = SortUtils.NEWEST

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun getTvShowsNotNull() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(10)
        val tv = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tv.value = dummyTvShow

        `when`(repository.getAllTvShows(sort)).thenReturn(tv)
        val tvEntities = viewModel.getTvShows(sort).value?.data
        verify(repository).getAllTvShows(sort)

        assertNotNull(tvEntities)

        viewModel.getTvShows(sort).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun getTvShowSizeEqualsTen() {
        val dummyTvShow = Resource.success(pagedList)
        `when`(dummyTvShow.data?.size).thenReturn(10)
        val tv = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tv.value = dummyTvShow

        `when`(repository.getAllTvShows(sort)).thenReturn(tv)
        val tvEntities = viewModel.getTvShows(sort).value?.data
        verify(repository).getAllTvShows(sort)

        assertEquals(10, tvEntities?.size)

        viewModel.getTvShows(sort).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}