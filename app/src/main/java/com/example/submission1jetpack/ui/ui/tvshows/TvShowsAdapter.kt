package com.example.submission1jetpack.ui.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.databinding.ItemListTvshowBinding
import com.example.submission1jetpack.utils.Constant
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback
import java.util.regex.Pattern

class TvShowsAdapter(
    private val callback: ItemClickCallback,
    private val listener: ShareCallback
) : RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    private val listTvShow = ArrayList<TvShowEntity>()

    fun setTvShows(tvShow: List<TvShowEntity>?) {
        if (tvShow == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsAdapter.ViewHolder {
        val binding =
            ItemListTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowsAdapter.ViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class ViewHolder(private val binding: ItemListTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.tvShowTitle
                val str = tvShow.tvShowRelease
                val delim = "-"
                val arr = Pattern.compile(delim).split(str.toString())
                tvYear.text = arr[0].toString()
                Glide.with(itemView.context)
                    .load(Constant.BASE_URL_IMAGE + tvShow.tvShowPoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
                imgShare.setOnClickListener {
                    tvShow.tvShowTitle?.let { title ->
                        listener.onShareClick(
                            title
                        )
                    }
                }
                itemView.setOnClickListener {
                    tvShow.tvShowId?.let { id ->
                        callback.onItemClicked(
                            id, "tvshow"
                        )
                    }
                }
            }
        }
    }
}