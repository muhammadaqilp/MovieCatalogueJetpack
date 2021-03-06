package com.example.submission1jetpack.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.databinding.ActivityDetailContentBinding

class DetailContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailContentViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val category = extras.getString(EXTRA_CATEGORY)
            val id = extras.getString(EXTRA_ID)
            if (category != null && id != null) {
                if (category == "movie") {
                    supportActionBar?.title = resources.getString(R.string.title_toolbar, "Movie")
                    viewModel.setSelectedMovieId(id)
                    populateMovie(viewModel.getMovie())
                } else {
                    supportActionBar?.title = resources.getString(R.string.title_toolbar, "TV Show")
                    viewModel.setSelectedTvShowId(id)
                    populateTvShow(viewModel.getTvShows())
                }
            }
        }
    }

    private fun populateMovie(movie: MovieEntity) {
        with(binding) {
            tvTitle.text = movie.movieTitle
            tvRelease.text = movie.movieRelease
            tvDuration.text = movie.movieDuration
            tvOverview.text = movie.movieOverview
            tvCategory.text = resources.getString(R.string.movie)
            ivPoster.tag = movie.moviePoster
            Glide.with(this@DetailContentActivity)
                .load(movie.moviePoster)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(ivPoster)
        }
    }

    private fun populateTvShow(tvShow: TvShowEntity) {
        with(binding) {
            binding.tvTitle.text = tvShow.tvShowTitle
            binding.tvRelease.text = tvShow.tvShowRelease
            binding.tvDuration.text = tvShow.tvShowDuration
            binding.tvOverview.text = tvShow.tvShowOverview
            binding.tvCategory.text = resources.getString(R.string.tv_show)
            binding.ivPoster.tag = tvShow.tvShowPoster
            Glide.with(this@DetailContentActivity)
                .load(tvShow.tvShowPoster)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.ivPoster)
        }
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