package com.example.submission1jetpack.ui.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.databinding.ItemListTvshowBinding
import com.example.submission1jetpack.utils.Constant
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback
import java.util.regex.Pattern

class TvShowsAdapter(
    private val callback: ItemClickCallback,
    private val listener: ShareCallback
) : PagedListAdapter<TvShowEntity, TvShowsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsAdapter.ViewHolder {
        val binding =
            ItemListTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowsAdapter.ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

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
                    tvShow.tvShowId.let { id ->
                        callback.onItemClicked(
                            id, "tvshow"
                        )
                    }
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}