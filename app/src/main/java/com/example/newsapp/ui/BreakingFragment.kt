package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapter.BreakingNewsAdapter
import com.example.newsapp.databinding.FragmentBreakingBinding
import com.example.newsapp.viewmodel.BreakingNewsViewModel
import kotlinx.coroutines.launch


class BreakingFragment : Fragment() {

    private lateinit var adapter: BreakingNewsAdapter
    private lateinit var recyclerView: RecyclerView

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

        recyclerView = binding.recyclerview

        viewModel = ViewModelProvider(this).get(BreakingNewsViewModel::class.java)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.LoadingText.isVisible = it
            binding.LoadingProgressbar.isVisible = it
        }
        viewModel.getBreakingNews()

        adapter = BreakingNewsAdapter { position ->
            val currentNews = adapter.currentList[position]
            val updatedArticle = currentNews.copy(isFavorites = !currentNews.isFavorites)
            lifecycleScope.launch {
                viewModel.updateNews(updatedArticle)
            }
        }
        viewModel.news?.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}