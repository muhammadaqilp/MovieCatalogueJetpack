package com.example.submission1jetpack.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.databinding.ItemListMovieBinding
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback
import java.util.regex.Pattern

class MoviesAdapter(
    private val callback: ItemClickCallback,
    private val listener: ShareCallback
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var listMovies = ArrayList<MovieEntity>()

    fun setMovies(movie: List<MovieEntity>?) {
        if (movie == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val binding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.movieTitle
                val str = movie.movieRelease
                val delim = "/"
                val arr = Pattern.compile(delim).split(str)
                tvYear.text = arr[2].toString()
                Glide.with(itemView.context)
                    .load(movie.moviePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
                imgShare.setOnClickListener { listener.onShareClick(movie.movieTitle) }
                itemView.setOnClickListener { callback.onItemClicked(movie.movieId, "movie") }
            }
        }
    }
}