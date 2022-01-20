package com.example.submission1jetpack.di

import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()

        return Repository.getInstance(remoteDataSource)
    }

}