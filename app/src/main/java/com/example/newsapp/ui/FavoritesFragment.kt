package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.newsapp.adapter.BreakingNewsAdapter
import com.example.newsapp.databinding.FragmentFavoritesBinding
import com.example.newsapp.model.ShowNews
import com.example.newsapp.viewmodel.FavoritesViewModel
import kotlinx.coroutines.launch


class FavoritesFragment : Fragment() {

    private lateinit var adapter: BreakingNewsAdapter
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoritesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        adapter = BreakingNewsAdapter { position, isFavorites ->
            val currentNews = adapter.currentList[position]
            if (isFavorites) {
                val updatedArticle = currentNews.copy(isFavorites = !currentNews.isFavorites)
                lifecycleScope.launch {
                    viewModel.updateNews(updatedArticle)
                }
            } else {
                val data =
                    ShowNews(currentNews.title, currentNews.urlToImage, currentNews.content)
                val action = FavoritesFragmentDirections.actionFavoritesFragmentToShowFragment(data)
                Navigation.findNavController(view).navigate(action)
            }
        }

        viewModel.favorites?.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }
        binding.recyclerview.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
