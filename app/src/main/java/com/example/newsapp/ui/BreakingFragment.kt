package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreakingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.setHasFixedSize(true)


        binding.LoadingProgressbar.visibility = View.VISIBLE
        binding.LoadingText.visibility = View.VISIBLE


        getBreakingNews { articleList: List<Article> ->
            binding.recyclerview.adapter = BreakingNewsAdapter(articleList)

            binding.LoadingProgressbar.visibility = View.INVISIBLE
            binding.LoadingText.visibility = View.INVISIBLE
        }
    }

    private fun getBreakingNews(callback: (List<Article>) -> Unit) {
        RetrofitClient.api.getBreakingNews().enqueue(object : Callback<NewResponse> {
            override fun onResponse(call: Call<NewResponse>, response: Response<NewResponse>) {
                return callback(response.body()!!.articles)
            }

            override fun onFailure(call: Call<NewResponse>, t: Throwable) {
                Log.d("TAG", "Veriyi Alamadım")
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}