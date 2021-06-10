package com.example.newsapp

import android.app.Application
import com.example.newsapp.service.BreakingNewsDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BreakingNewsDatabase.initializeDatabase(applicationContext)
    }
}