package com.example.submission1jetpack.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResources.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        ApiConfig.getApiService().getPopularMovies()
            .enqueue(object : Callback<DataResponse<MovieResponse>> {
                override fun onResponse(
                    call: Call<DataResponse<MovieResponse>>,
                    response: Response<DataResponse<MovieResponse>>
                ) {
                    if (response.isSuccessful) {
                        EspressoIdlingResources.decrement()
                        if (response.body() == null) {
                            resultMovies.value = ApiResponse.empty("No data", mutableListOf())
                        } else {
                            resultMovies.value = ApiResponse.success(response.body()?.results!!)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<DataResponse<MovieResponse>>,
                    t: Throwable
                ) {
                    EspressoIdlingResources.decrement()
                    resultMovies.value = ApiResponse.error(t.message.toString(), mutableListOf())
                    Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
                }
            })
        return resultMovies
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResources.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        ApiConfig.getApiService().getPopularTvShows()
            .enqueue(object : Callback<DataResponse<TvShowResponse>> {
                override fun onResponse(
                    call: Call<DataResponse<TvShowResponse>>,
                    response: Response<DataResponse<TvShowResponse>>
                ) {
                    if (response.isSuccessful) {
                        EspressoIdlingResources.decrement()
                        if (response.body() == null) {
                            resultTvShows.value = ApiResponse.empty("No data", mutableListOf())
                        } else {
                            resultTvShows.value = ApiResponse.success(response.body()?.results!!)
                        }
                    }
                }

                override fun onFailure(call: Call<DataResponse<TvShowResponse>>, t: Throwable) {
                    EspressoIdlingResources.decrement()
                    resultTvShows.value = ApiResponse.error(t.message.toString(), mutableListOf())
                    Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
                }
            })
        return resultTvShows
    }

    fun getDetailMovies(id: String): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResources.increment()
        val resultDetail = MutableLiveData<ApiResponse<MovieResponse>>()
        ApiConfig.getApiService().getMovieDetail(id).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    EspressoIdlingResources.decrement()
                    if (response.body() == null) {
                        resultDetail.value = ApiResponse.empty("No data", null!!)
                    } else {
                        resultDetail.value = ApiResponse.success(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                resultDetail.value = ApiResponse.error(t.message.toString(), null!!)
                Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
            }

        })
        return resultDetail
    }

    fun getDetailTvShows(id: String): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResources.increment()
        val resultDetail = MutableLiveData<ApiResponse<TvShowResponse>>()
        ApiConfig.getApiService().getTvShowDetail(id).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful) {
                    EspressoIdlingResources.decrement()
                    if (response.body() == null) {
                        resultDetail.value = ApiResponse.empty("No data", null!!)
                    } else {
                        resultDetail.value = ApiResponse.success(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                EspressoIdlingResources.decrement()
                resultDetail.value = ApiResponse.error(t.message.toString(), null!!)
                Log.d(RemoteDataSource::class.java.simpleName, "onFailure: " + t.message)
            }

        })
        return resultDetail
    }

}