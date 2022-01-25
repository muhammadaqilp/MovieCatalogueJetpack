package com.example.submission1jetpack.ui.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1jetpack.R
import com.example.submission1jetpack.data.source.local.entity.TvShowEntity
import com.example.submission1jetpack.databinding.FragmentTvShowsBinding
import com.example.submission1jetpack.ui.ui.detail.DetailContentActivity
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback
import com.example.submission1jetpack.utils.SortUtils
import com.example.submission1jetpack.viewmodel.ViewModelFactory
import com.example.submission1jetpack.vo.Resource
import com.example.submission1jetpack.vo.Status

class TvShowsFragment : Fragment(), ShareCallback, ItemClickCallback {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TvShowsViewModel
    private lateinit var tvShowsAdapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]
            tvShowsAdapter = TvShowsAdapter(this, this)

            viewModel.getTvShows(SortUtils.NEWEST).observe(viewLifecycleOwner, observer)

            with(binding.rvTvshows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }

            generateSpinner()
        }
    }

    private val observer = Observer<Resource<PagedList<TvShowEntity>>> { tvShows ->
        when (tvShows.status) {
            Status.LOADING -> loading(true)
            Status.SUCCESS -> {
                loading(false)
                tvShowsAdapter.submitList(tvShows.data)
            }
            Status.ERROR -> {
                loading(false)
                Toast.makeText(context, "Terjadi kesalahan!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateSpinner() {
        val sortby = resources.getStringArray(R.array.Sort)
        val adapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                sortby
            )
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ) {
                    binding.tvSort.text = getString(R.string.txt_sortby, sortby[p2])
                    var sort = ""
                    when (p2) {
                        0 -> sort = SortUtils.NEWEST
                        1 -> sort = SortUtils.OLDEST
                        2 -> sort = SortUtils.RANDOM
                    }
                    viewModel.getTvShows(sort).observe(viewLifecycleOwner, observer)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
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