package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.FragmentShowBinding


class ShowFragment : Fragment() {

    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding!!

    private val args: ShowFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.showNewsTitle.text = args.showNews.title
        binding.showNewsContent.text = args.showNews.content
        Glide.with(binding.showNewsImage).load(args.showNews.image).into(binding.showNewsImage)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}