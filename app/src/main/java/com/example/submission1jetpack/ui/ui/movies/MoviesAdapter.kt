package com.example.submission1jetpack.ui.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.source.local.entity.MovieEntity
import com.example.submission1jetpack.databinding.ItemListMovieBinding
import com.example.submission1jetpack.utils.Constant
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback
import java.util.regex.Pattern

class MoviesAdapter(
    private val callback: ItemClickCallback,
    private val listener: ShareCallback
) : PagedListAdapter<MovieEntity, MoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val binding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    inner class ViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.movieTitle
                val str = movie.movieRelease
                val delim = "-"
                val arr = Pattern.compile(delim).split(str.toString())
                tvYear.text = arr[0].toString()
                Glide.with(itemView.context)
                    .load(Constant.BASE_URL_IMAGE + movie.moviePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
                imgShare.setOnClickListener {
                    movie.movieTitle?.let { title ->
                        listener.onShareClick(
                            title
                        )
                    }
                }
                itemView.setOnClickListener {
                    movie.movieId.let { id ->
                        callback.onItemClicked(
                            id,
                            "movie"
                        )
                    }
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}