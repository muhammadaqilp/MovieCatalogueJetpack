package com.example.submission1jetpack.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1jetpack.R
import com.example.submission1jetpack.databinding.FragmentTvShowsBinding
import com.example.submission1jetpack.ui.detail.DetailContentActivity
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback

class TvShowsFragment : Fragment(), ShareCallback, ItemClickCallback {

    private lateinit var binding: FragmentTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowsViewModel::class.java]
            val tvShows = viewModel.getTvShows()
            if (tvShows.isEmpty()) {
                binding.rvTvshows.visibility = View.GONE
                binding.ivNodata.visibility = View.VISIBLE
            } else {
                val tvShowsAdapter = TvShowsAdapter(this, this)
                tvShowsAdapter.setTvShows(tvShows)

                with(binding.rvTvshows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowsAdapter
                }
            }
        }
    }

    override fun onShareClick(title: String) {
        if (activity != null) {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_text, title))
            startActivity(Intent.createChooser(intent, "Share to Your Friends"))
        }
    }

    override fun onItemClicked(id: String, category: String) {
        val intent = Intent(context, DetailContentActivity::class.java)
        intent.putExtra(DetailContentActivity.EXTRA_ID, id)
        intent.putExtra(DetailContentActivity.EXTRA_CATEGORY, category)
        startActivity(intent)
    }

}