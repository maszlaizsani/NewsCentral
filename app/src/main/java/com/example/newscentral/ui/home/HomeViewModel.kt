package com.example.newscentral.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newscentral.APImodel.Article

class HomeViewModel : ViewModel() {

    private val _navigateToDetail = MutableLiveData<Article>()
    val navigateToDetail: LiveData<Article>
        get() = _navigateToDetail

    fun displayArticleDetails(article: Article) {
        _navigateToDetail.value = article
    }

    fun displayArticleDetailsComplete() {
        _navigateToDetail.value = null
    }
}