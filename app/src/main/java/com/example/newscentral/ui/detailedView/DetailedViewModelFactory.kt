package com.example.newscentral.ui.detailedView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newscentral.APImodel.Article

class DetailedViewModelFactory(private val article: Article) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedViewModel::class.java)) {
            return DetailedViewModel(article) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}