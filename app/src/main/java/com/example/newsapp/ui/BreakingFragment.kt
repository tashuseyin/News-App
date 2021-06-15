package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapter.BreakingNewsAdapter
import com.example.newsapp.databinding.FragmentBreakingBinding
import com.example.newsapp.model.Article
import com.example.newsapp.viewmodel.BreakingNewsViewModel


class BreakingFragment : Fragment() {

    private lateinit var articleList: List<Article>
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
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                binding.LoadingText.isVisible = it
                binding.LoadingProgressbar.isVisible = it
            }

            getBreakingNews()
            news.observe(viewLifecycleOwner) {
                articleList = it
                adapter = BreakingNewsAdapter {
                    adapter.submitList(null)
                    adapter.submitList(articleList.toList())
                }
                adapter.submitList(articleList.toList())
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = adapter

            }
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}