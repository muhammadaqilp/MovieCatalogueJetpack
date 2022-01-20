package com.example.submission1jetpack.data.source.remote

import android.util.Log
import com.example.submission1jetpack.data.source.remote.api.ApiConfig
import com.example.submission1jetpack.data.source.remote.response.DataResponse
import com.example.submission1jetpack.data.source.remote.response.MovieResponse
import com.example.submission1jetpack.data.source.remote.response.TvShowResponse
import com.example.submission1jetpack.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResources.increment()
        ApiConfig.getApiService().getPopularMovies()
            .enqueue(object : Callback<DataResponse<MovieResponse>> {
                override fun onResponse(
                    call: Call<DataResponse<MovieResponse>>,
                    response: Response<DataResponse<MovieResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            it.results?.let { results -> callback.onAllMoviesReceived(results) }
                        }
                        EspressoIdlingResources.decrement()
                    }
                }

                override fun onFailure(
                    call: Call<DataResponse<MovieResponse>>,
                    t: Throwable
                ) {
                    EspressoIdlingResources.decrement()
                    Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
                }
            })
    }

    fun getAllTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResources.increment()
        ApiConfig.getApiService().getPopularTvShows()
            .enqueue(object : Callback<DataResponse<TvShowResponse>> {
                override fun onResponse(
                    call: Call<DataResponse<TvShowResponse>>,
                    response: Response<DataResponse<TvShowResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            it.results?.let { results -> callback.onAllTvShowsReceived(results) }
                        }
                        EspressoIdlingResources.decrement()
                    }
                }

                override fun onFailure(call: Call<DataResponse<TvShowResponse>>, t: Throwable) {
                    EspressoIdlingResources.decrement()
                    Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
                }

            })
    }

    fun getDetailMovies(id: String, callback: LoadMoviesDetailCallback) {
        EspressoIdlingResources.increment()
        ApiConfig.getApiService().getMovieDetail(id).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onMoviesDetailReceived(it)
                    }
                    EspressoIdlingResources.decrement()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
            }

        })
    }

    fun getDetailTvShows(id: String, callback: LoadTvShowsDetailCallback) {
        EspressoIdlingResources.increment()
        ApiConfig.getApiService().getTvShowDetail(id).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onTvShowsDetailReceived(it)
                    }
                    EspressoIdlingResources.decrement()
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
            }

        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface LoadMoviesDetailCallback {
        fun onMoviesDetailReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowsDetailCallback {
        fun onTvShowsDetailReceived(tvShowResponse: TvShowResponse)
    }

}