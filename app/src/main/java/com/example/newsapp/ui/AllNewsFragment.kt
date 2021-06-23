package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.newsapp.adapter.BreakingNewsPagingAdapter
import com.example.newsapp.databinding.FragmentAllNewsBinding
import com.example.newsapp.model.ShowNews
import com.example.newsapp.viewmodel.AllNewsViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AllNewsFragment : Fragment() {

    private lateinit var adapter: BreakingNewsPagingAdapter
    private lateinit var viewModel: AllNewsViewModel
    private var _binding: FragmentAllNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AllNewsViewModel::class.java)



        adapter = BreakingNewsPagingAdapter { position, isFavorites ->
            val currentNews = adapter.snapshot()[position]
            if (isFavorites) {
                val updatedArticle = currentNews?.copy(isFavorites = !currentNews.isFavorites)
                lifecycleScope.launch {
                    if (updatedArticle != null) {
                        viewModel.updateNews(updatedArticle)
                    }
                }
            } else {
                currentNews?.let {
                    val data = ShowNews(
                        currentNews.urlToImage,
                        currentNews.title,
                        currentNews.description,
                        currentNews.url
                    )
                    val action =
                        AllNewsFragmentDirections.actionAllNewsFragmentToDetailNewsFragment(data)
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.listData.collect {
                adapter.submitData(it)
            }
        }

        binding.recyclerview.adapter = adapter

        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                binding.LoadingProgressbar.isVisible = it
            }
            getBreakingNews()
            isRefreshing.observe(viewLifecycleOwner) {
                binding.swipeRefreshLayout.isRefreshing = it
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}