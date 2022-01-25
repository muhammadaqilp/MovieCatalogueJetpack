package com.example.submission1jetpack.ui.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission1jetpack.R
import com.example.submission1jetpack.databinding.FragmentAllFavoriteBinding
import com.example.submission1jetpack.ui.ui.detail.DetailContentActivity
import com.example.submission1jetpack.ui.ui.movies.MoviesAdapter
import com.example.submission1jetpack.ui.ui.tvshows.TvShowsAdapter
import com.example.submission1jetpack.utils.ItemClickCallback
import com.example.submission1jetpack.utils.ShareCallback
import com.example.submission1jetpack.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AllFavoriteFragment : Fragment(), ItemClickCallback, ShareCallback {

    private var _binding: FragmentAllFavoriteBinding? = null
    private val binding get() = _binding!!
    private var type: String? = null
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var tvAdapter: TvShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString("type", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAllFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rv)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            if (type != null) {
                if (type.equals("movie")) {
                    loading(true)
                    moviesAdapter = MoviesAdapter(this, this)
                    viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                        loading(false)
                        moviesAdapter.submitList(movies)
                    })

                    with(binding.rv) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = moviesAdapter
                    }
                } else {
                    loading(true)
                    tvAdapter = TvShowsAdapter(this, this)
                    viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, { tv ->
                        loading(false)
                        tvAdapter.submitList(tv)

                    })

                    with(binding.rv) {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvAdapter
                    }
                }
            }
        }
    }

    private fun loading(status: Boolean) {
        binding.rv.visibility = if (!status) View.VISIBLE else View.GONE
        binding.progressBar.visibility = if (status) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(id: String, category: String) {
        val intent = Intent(context, DetailContentActivity::class.java)
        intent.putExtra(DetailContentActivity.EXTRA_ID, id)
        intent.putExtra(DetailContentActivity.EXTRA_CATEGORY, category)
        startActivity(intent)
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

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                if (type == "movie") {
                    val swipedPosition = viewHolder.absoluteAdapterPosition
                    val movieEntity = moviesAdapter.getSwipedData(swipedPosition)
                    movieEntity?.let { viewModel.setFavoriteMovie(it) }

                    val snackbar =
                        Snackbar.make(view as View, R.string.undo_delete, Snackbar.LENGTH_LONG)
                    snackbar.setAction(R.string.yes) { _ ->
                        movieEntity?.let { viewModel.setFavoriteMovie(it) }
                    }
                    snackbar.show()
                } else {
                    val swipedPosition = viewHolder.absoluteAdapterPosition
                    val tvEntity = tvAdapter.getSwipedData(swipedPosition)
                    tvEntity?.let { viewModel.setFavoriteTvShow(it) }

                    val snackbar =
                        Snackbar.make(view as View, R.string.undo_delete, Snackbar.LENGTH_LONG)
                    snackbar.setAction(R.string.yes) { _ ->
                        tvEntity?.let { viewModel.setFavoriteTvShow(it) }
                    }
                    snackbar.show()
                }
            }
        }

    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(type: String) =
            AllFavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString("type", type)
                }
            }
    }
}