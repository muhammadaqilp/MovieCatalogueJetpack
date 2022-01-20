package com.example.submission1jetpack.ui.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.databinding.ActivityDetailContentBinding
import com.example.submission1jetpack.utils.Constant
import com.example.submission1jetpack.viewmodel.ViewModelFactory

class DetailContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailContentViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val category = extras.getString(EXTRA_CATEGORY)
            val id = extras.getString(EXTRA_ID)
            if (category != null && id != null) {
                if (category == "movie") {
                    supportActionBar?.title = resources.getString(R.string.title_toolbar, "Movie")
                    viewModel.setSelectedMovieId(id)
                    viewModel.getMovie().observe(this, { movies ->
                        populateMovie(movies)
                    })
                } else {
                    supportActionBar?.title = resources.getString(R.string.title_toolbar, "TV Show")
                    viewModel.setSelectedTvShowId(id)
                    viewModel.getTvShows().observe(this, { tvShows ->
                        populateTvShow(tvShows)
                    })
                }
            }
        }
    }

    private fun populateMovie(movie: MovieEntity) {
        with(binding) {
            tvTitle.text = movie.movieTitle
            tvRelease.text = movie.movieRelease
            tvDuration.text = resources.getString(R.string.tv_duration, movie.movieDuration)
            tvOverview.text = movie.movieOverview
            tvCategory.text = resources.getString(R.string.movie)
            ivPoster.tag = movie.moviePoster
            Glide.with(this@DetailContentActivity)
                .load(Constant.BASE_URL_IMAGE + movie.moviePoster)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ivPoster)
        }
    }

    private fun populateTvShow(tvShow: TvShowEntity) {
        binding.tvTitle.text = tvShow.tvShowTitle
        binding.tvRelease.text = tvShow.tvShowRelease
        binding.tvDuration.text = resources.getString(R.string.tv_duration, tvShow.tvShowDuration)
        binding.tvOverview.text = tvShow.tvShowOverview
        binding.tvCategory.text = resources.getString(R.string.tv_show)
        binding.ivPoster.tag = tvShow.tvShowPoster
        Glide.with(this@DetailContentActivity)
            .load(Constant.BASE_URL_IMAGE + tvShow.tvShowPoster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.ivPoster)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
        const val EXTRA_ID = "extra_id"
    }
}