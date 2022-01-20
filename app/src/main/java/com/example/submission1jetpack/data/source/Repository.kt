package com.example.submission1jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.data.source.remote.RemoteDataSource
import com.example.submission1jetpack.data.source.remote.response.MovieResponse
import com.example.submission1jetpack.data.source.remote.response.TvShowResponse

class Repository private constructor(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val moviesResults = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.id.toString(),
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.runtime.toString(),
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                moviesResults.postValue(movieList)
            }
        })
        return moviesResults
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowsResults = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse) {
                    val tvShow = TvShowEntity(
                        response.id.toString(),
                        response.name,
                        response.overview,
                        response.firstAirDate,
                        response.episodeRunTime?.get(0)?.toString(),
                        response.posterPath
                    )
                    tvShowList.add(tvShow)
                }
                tvShowsResults.postValue(tvShowList)
            }
        })
        return tvShowsResults
    }

    override fun getDetailMovies(movieId: String): LiveData<MovieEntity> {
        val moviesResults = MutableLiveData<MovieEntity>()

        remoteDataSource.getDetailMovies(
            movieId,
            object : RemoteDataSource.LoadMoviesDetailCallback {
                override fun onMoviesDetailReceived(movieResponse: MovieResponse) {
                    val movie = MovieEntity(
                        movieResponse.id.toString(),
                        movieResponse.title,
                        movieResponse.overview,
                        movieResponse.releaseDate,
                        movieResponse.runtime.toString(),
                        movieResponse.posterPath
                    )
                    moviesResults.postValue(movie)
                }
            })
        return moviesResults
    }

    override fun getDetailTvShows(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResults = MutableLiveData<TvShowEntity>()

        remoteDataSource.getDetailTvShows(
            tvShowId,
            object : RemoteDataSource.LoadTvShowsDetailCallback {
                override fun onTvShowsDetailReceived(tvShowResponse: TvShowResponse) {
                    val tvShow = TvShowEntity(
                        tvShowResponse.id.toString(),
                        tvShowResponse.name,
                        tvShowResponse.overview,
                        tvShowResponse.firstAirDate,
                        tvShowResponse.episodeRunTime?.get(0)?.toString(),
                        tvShowResponse.posterPath
                    )
                    tvShowResults.postValue(tvShow)
                }
            })
        return tvShowResults
    }
}