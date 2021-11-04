package com.example.newsapp.ui.favorites

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.MyApplication
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentFavoritesBinding
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.domain.model.News
import com.example.newsapp.ui.news.NewsAdapter
import com.example.newsapp.ui.news.NewsViewModel
import com.example.newsapp.ui.news.NewsViewModelFactory
import javax.inject.Inject

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoritesViewModel: FavoritesViewModel

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    private lateinit var favoritesRecyclerAdapter: FavoritesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        initNewsRecyclerView()

        setupObservers()
    }

    private fun setupViewModel() {
        favoritesViewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(FavoritesViewModel::class.java)
    }

    private fun initNewsRecyclerView() {
        favoritesRecyclerAdapter = FavoritesAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorites.layoutManager = layoutManager

        binding.rvFavorites.setHasFixedSize(true)
        binding.rvFavorites.adapter = favoritesRecyclerAdapter

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            layoutManager.orientation
        )
        binding.rvFavorites.addItemDecoration(dividerItemDecoration)
    }

    private fun setupObservers() {
        favoritesViewModel.getFavoriteNews().observe(viewLifecycleOwner) { response ->
            when {
                response == null -> {
                    favoritesViewModel.setTextMessage(true)
                    return@observe
                }
                response.isEmpty() -> {
                    favoritesViewModel.setTextMessage(true)
                }
                else -> {
                    favoritesViewModel.setTextMessage(false)
                }
            }

            binding.rvFavorites.visibility = View.VISIBLE

            addItemsToFavoritesRecyclerView(response)
        }

        favoritesViewModel.textMessageLiveData.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.tvMessageFavorites.visibility = View.VISIBLE
            } else {
                binding.tvMessageFavorites.visibility = View.GONE
            }
        }
    }

    private fun addItemsToFavoritesRecyclerView(items: List<News>) {
        favoritesRecyclerAdapter.addItems(items)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoritesFragment()
    }
}