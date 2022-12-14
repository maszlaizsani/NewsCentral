package com.example.newscentral.ui.detailedView

import androidx.lifecycle.ViewModel
import com.example.newscentral.APImodel.Article

class DetailedViewModel(val article: Article) : ViewModel() {

    fun getArticleObject(): Article {
        return article
    }

    fun getTitle(): String {
        return article.title
    }

    fun getImageUrl(): String {
        return article.urlToImage
    }

    fun getContent(): String {
        return article.content
    }

    fun getUrl(): String {
        return article.url
    }
}