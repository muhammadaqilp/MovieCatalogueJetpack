package com.example.submission1jetpack.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.databinding.ItemListTvshowBinding
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback

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
                tvYear.text = tvShow.tvShowRelease
                Glide.with(itemView.context)
                    .load(tvShow.tvShowPoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivPoster)
                imgShare.setOnClickListener { listener.onShareClick(tvShow.tvShowTitle) }
                itemView.setOnClickListener { callback.onItemClicked(tvShow.tvShowId, "tvshow") }
            }
        }
    }
}