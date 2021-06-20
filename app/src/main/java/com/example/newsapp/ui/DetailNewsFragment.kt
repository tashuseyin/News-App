package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.FragmentDetailNewsBinding


class DetailNewsFragment : Fragment() {

    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailNewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showNewsTitle.text = args.detailNews.title
        binding.showNewsContent.text = args.detailNews.description
        Glide.with(binding.showNewsImage).load(args.detailNews.urlToImage).into(binding.showNewsImage)

        binding.goShowNewsWebView.setOnClickListener {
            val action =
                DetailNewsFragmentDirections.actionDetailNewsFragmentToShowFragment(args.detailNews.url)
            Navigation.findNavController(view).navigate(action)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}