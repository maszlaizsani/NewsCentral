package com.example.newscentral.ui.savedArticles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedArticlesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Saved Articles Fragment"
    }
    val text: LiveData<String> = _text
}