package com.example.newscentral.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedArticleEntity::class], version = 1, exportSchema = false)
abstract class SavedArticleDatabase : RoomDatabase() {

    abstract val savedArticleDatabaseDao: SavedArticleDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: SavedArticleDatabase? = null

        fun getInstance(context: Context): SavedArticleDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        SavedArticleDatabase::class.java,
                        "saved_articles"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return  instance
            }
        }
    }
}