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
import com.example.newsapp.adapter.BreakingNewsAdapter
import com.example.newsapp.databinding.FragmentBreakingBinding
import com.example.newsapp.viewmodel.BreakingNewsViewModel
import kotlinx.coroutines.launch


class BreakingFragment : Fragment() {

    private lateinit var adapter: BreakingNewsAdapter
    private lateinit var viewModel: BreakingNewsViewModel
    private var _binding: FragmentBreakingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BreakingNewsViewModel::class.java)

        adapter = BreakingNewsAdapter { position, isFavorites ->
            val currentNews = adapter.currentList[position]
            if (isFavorites) {
                val updatedArticle = currentNews.copy(isFavorites = !currentNews.isFavorites)
                lifecycleScope.launch {
                    viewModel.updateNews(updatedArticle)
                }
            } else {
                val action =
                    BreakingFragmentDirections.actionBreakingFragmentToShowFragment(currentNews.url)
                Navigation.findNavController(view).navigate(action)
            }
        }
        binding.recyclerview.adapter = adapter

        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                binding.LoadingProgressbar.isVisible = it
                binding.LoadingText.isVisible = it
            }
            getBreakingNews()
            news?.observe(viewLifecycleOwner) {
                adapter.submitList(it.toList())
            }
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