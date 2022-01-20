package com.example.submission1jetpack.data.source.remote.api

import com.example.submission1jetpack.data.source.remote.response.MovieResponse
import com.example.submission1jetpack.data.source.remote.response.DataResponse
import com.example.submission1jetpack.data.source.remote.response.TvShowResponse
import com.example.submission1jetpack.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id: String,
        @Query("api_key") apiKey: String = Constant.TOKEN,
        @Query("language") language: String = Constant.LANGUAGE
    ): Call<MovieResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") id: String,
        @Query("api_key") apiKey: String = Constant.TOKEN,
        @Query("language") language: String = Constant.LANGUAGE
    ): Call<TvShowResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String = Constant.TOKEN,
        @Query("language") language: String = Constant.LANGUAGE
    ): Call<DataResponse<TvShowResponse>>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = Constant.TOKEN,
        @Query("language") language: String = Constant.LANGUAGE
    ): Call<DataResponse<MovieResponse>>
}