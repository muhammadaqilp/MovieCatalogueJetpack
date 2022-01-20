package com.example.submission1jetpack.ui.ui.tvshows

import android.annotation.SuppressLint
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
import com.example.submission1jetpack.ui.ui.detail.DetailContentActivity
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback
import com.example.submission1jetpack.viewmodel.ViewModelFactory

class TvShowsFragment : Fragment(), ShareCallback, ItemClickCallback {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]
            val tvShowsAdapter = TvShowsAdapter(this, this)

            loading(true)
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                loading(false)
                tvShowsAdapter.setTvShows(tvShows)
                tvShowsAdapter.notifyDataSetChanged()
            })

            with(binding.rvTvshows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
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

    private fun loading(status: Boolean) {
        binding.rvTvshows.visibility = if (!status) View.VISIBLE else View.GONE
        binding.progressBar.visibility = if (status) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}