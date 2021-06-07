package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.BreakingNewsAdapter
import com.example.newsapp.api.RetrofitClient
import com.example.newsapp.databinding.FragmentBreakingBinding
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BreakingFragment : Fragment() {


    private var _binding: FragmentBreakingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BreakingNewsViewModel

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
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.setHasFixedSize(true)

        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.LoadingText.isVisible = it
            binding.LoadingProgressbar.isVisible = it
        }

        viewModel.getBreakingNews()
        viewModel.news.observe(viewLifecycleOwner){
            binding.recyclerview.adapter = BreakingNewsAdapter(it)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}