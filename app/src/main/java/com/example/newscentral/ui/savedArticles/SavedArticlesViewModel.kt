package com.example.newscentral.ui.savedArticles

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.newscentral.database.SavedArticleDatabase
import com.example.newscentral.database.SavedArticleDatabaseDao
import com.example.newscentral.database.SavedArticleEntity

class SavedArticlesViewModel(val dataSource: SavedArticleDatabaseDao, application: Application) :
    ViewModel() {

    fun getSavedArticles(): LiveData<List<SavedArticleEntity>> {
        return Transformations.map(dataSource.getAll()){
            return@map it
        }
    }

}