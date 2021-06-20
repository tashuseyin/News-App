package com.example.newsapp

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation: BottomNavigationView = binding.bottomNavigaiton
        val navController = findNavController(R.id.navigation_fragment_activity_main)
        AppBarConfiguration.Builder(
            R.id.breakingFragment,
            R.id.allNewsFragment,
            R.id.favoritesFragment
        ).build()

        bottomNavigation.setupWithNavController(navController)
    }


}

