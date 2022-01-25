package com.example.submission1jetpack.di

import android.content.Context
import com.example.submission1jetpack.data.source.Repository
import com.example.submission1jetpack.data.source.local.LocalDataSource
import com.example.submission1jetpack.data.source.local.room.CatalogueDatabase
import com.example.submission1jetpack.data.source.remote.RemoteDataSource
import com.example.submission1jetpack.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): Repository {

        val database = CatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}