package com.example.newscentral.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newscentral.APImodel.Article

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Top Hungarian Articles Right Now"
    }
    val text: LiveData<String> = _text

   /* fun onArticleClicked(article: Article) {
        _navigateToDetail.value = article
    }*/
}