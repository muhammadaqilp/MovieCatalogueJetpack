package com.example.submission1jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.di.Injection
import com.example.submission1jetpack.ui.ui.detail.DetailContentViewModel
import com.example.submission1jetpack.ui.ui.favorite.FavoriteViewModel
import com.example.submission1jetpack.ui.ui.movies.MoviesViewModel
import com.example.submission1jetpack.ui.ui.tvshows.TvShowsViewModel

class ViewModelFactory private constructor(private val mRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(DetailContentViewModel::class.java) -> {
                DetailContentViewModel(mRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}