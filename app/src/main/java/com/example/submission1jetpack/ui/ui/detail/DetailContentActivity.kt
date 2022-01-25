package com.example.submission1jetpack.ui.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.databinding.ActivityDetailContentBinding
import com.example.submission1jetpack.utils.Constant
import com.example.submission1jetpack.viewmodel.ViewModelFactory
import com.example.submission1jetpack.vo.Status

class DetailContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailContentBinding
    private lateinit var viewModel: DetailContentViewModel
    private var menu: Menu? = null
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailContentViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            category = extras.getString(EXTRA_CATEGORY)
            val id = extras.getString(EXTRA_ID)
            if (category != null && id != null) {
                if (category == "movie") {
                    supportActionBar?.title = resources.getString(R.string.title_toolbar, "Movie")
                    viewModel.setSelectedMovieId(id)
                    viewModel.movieList.observe(this, { movie ->
                        if (movie != null) {
                            when (movie.status) {
                                Status.LOADING -> loading(true)
                                Status.SUCCESS -> if (movie.data != null) {
                                    loading(false)
                                    populateMovie(movie.data)
                                }
                                Status.ERROR -> {
                                    loading(false)
                                    Toast.makeText(this, "Terjadi Kesalahan!", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    })
                } else {
                    supportActionBar?.title = resources.getString(R.string.title_toolbar, "TV Show")
                    viewModel.setSelectedTvShowId(id)
                    viewModel.tvshowList.observe(this, { tvshow ->
                        if (tvshow != null) {
                            when (tvshow.status) {
                                Status.LOADING -> loading(true)
                                Status.SUCCESS -> if (tvshow.data != null) {
                                    loading(false)
                                    populateTvShow(tvshow.data)
                                }
                                Status.ERROR -> {
                                    loading(false)
                                    Toast.makeText(this, "Terjadi Kesalahan!", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (category == "movie") {
            viewModel.movieList.observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> {}
                        Status.SUCCESS -> if (movie.data != null) {
                            val state = movie.data.isFavorite
                            setFavoriteState(state)
                        }
                        Status.ERROR -> Toast.makeText(
                            this,
                            "Terjadi kesalahan!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        } else {
            viewModel.tvshowList.observe(this, { tvshow ->
                if (tvshow != null) {
                    when (tvshow.status) {
                        Status.LOADING -> {}
                        Status.SUCCESS -> if (tvshow.data != null) {
                            val state = tvshow.data.isFavorite
                            setFavoriteState(state)
                        }
                        Status.ERROR -> Toast.makeText(
                            this,
                            "Terjadi kesalahan!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
        return true
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuitem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuitem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited)
        } else {
            menuitem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        if (item.itemId == R.id.action_favorite) {
            return if (category == "movie") {
                viewModel.setFavoriteMovie()
                true
            } else {
                viewModel.setFavoriteTvShow()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loading(status: Boolean) {
        binding.nested.visibility = if (!status) View.VISIBLE else View.GONE
        binding.progressBar.visibility = if (status) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_CATEGORY = "extra_category"
        const val EXTRA_ID = "extra_id"
    }
}