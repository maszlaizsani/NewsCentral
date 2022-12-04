package com.example.newscentral.ui.savedArticles


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newscentral.database.SavedArticleDatabaseDao

class SavedArticlesViewModelFactory(
    private val dataSource: SavedArticleDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedArticlesViewModel::class.java)) {
            return SavedArticlesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
