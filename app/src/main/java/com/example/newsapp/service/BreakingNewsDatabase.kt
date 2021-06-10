package com.example.newsapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.Article


@Database(entities = [Article::class], version = 1)
@TypeConverters(Converter::class)
abstract class BreakingNewsDatabase : RoomDatabase() {

    abstract fun breakingNewsDao(): BreakingNewsDao

    companion object {
        @Volatile
        private var INSTANCE: BreakingNewsDatabase? = null

        fun initializeDatabase(context: Context) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BreakingNewsDatabase::class.java,
                    "breakingNews"
                ).build()
            }
        }

        fun getDatabase() = INSTANCE
    }
}

