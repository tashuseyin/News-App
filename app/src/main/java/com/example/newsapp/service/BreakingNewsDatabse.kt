package com.example.newsapp.service

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class BreakingNewsDatabase : RoomDatabase() {

    abstract fun breakingNewsDao(): BreakingNewsDao

    companion object {
        @Volatile
        private var INSTANCE: BreakingNewsDatabase? = null

        fun getBreakingNews(context: Context): BreakingNewsDatabase {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    BreakingNewsDatabase::class.java,
                    "breakingNews"
                ).build()
                instance = tempInstance
                return tempInstance
            }
        }
    }
}

