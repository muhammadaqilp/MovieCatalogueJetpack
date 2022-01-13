package com.example.submission1jetpack.ui.tvshows

import androidx.lifecycle.ViewModel
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.utils.DataDummy

class TvShowsViewModel: ViewModel() {

    fun getTvShows(): List<TvShowEntity> = DataDummy.generateTvShowsData()

}