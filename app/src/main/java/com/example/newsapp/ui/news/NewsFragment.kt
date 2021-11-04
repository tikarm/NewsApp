package com.example.newsapp.ui.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.MyApplication
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.domain.model.News
import javax.inject.Inject

class NewsFragment : Fragment(), NewsAdapter.OnItemClickListener {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private lateinit var newsRecyclerAdapter: NewsAdapter

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
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        initNewsRecyclerView()

        setupObservers()
    }

    private fun setupViewModel() {
        newsViewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(NewsViewModel::class.java)
    }

    private fun initNewsRecyclerView() {
        newsRecyclerAdapter = NewsAdapter(this)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.layoutManager = layoutManager

        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.adapter = newsRecyclerAdapter

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            layoutManager.orientation
        )
        binding.rvNews.addItemDecoration(dividerItemDecoration)
    }

    private fun setupObservers() {
        newsViewModel.getNewsFromLocal().observe(viewLifecycleOwner) { response ->
            when {
                response == null -> {
                    newsViewModel.setTextMessage(true)
                    return@observe
                }
                response.isEmpty() -> {
                    newsViewModel.setTextMessage(true)
                }
                else -> {
                    newsViewModel.setTextMessage(false)
                }
            }
            newsViewModel.setProgressBar(false)

            binding.rvNews.visibility = View.VISIBLE

            addItemsToNewsRecyclerView(response)
        }

        newsViewModel.loadingLiveData.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.pbNews.visibility = View.VISIBLE
            } else {
                binding.pbNews.visibility = View.GONE
            }
        }

        newsViewModel.textMessageLiveData.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.tvMessageNews.visibility = View.VISIBLE
            } else {
                binding.tvMessageNews.visibility = View.GONE
            }
        }

        newsViewModel.getNews()
    }

    private fun addItemsToNewsRecyclerView(items: List<News>) {
        newsRecyclerAdapter.addItems(items)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NewsFragment()
    }

    override fun onCheckBoxCheckChanged(isChecked: Boolean, id: String) {
        newsViewModel.setNewsAsFavorite(isChecked, id)
    }
}