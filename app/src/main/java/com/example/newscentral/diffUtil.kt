package com.example.newscentral

import androidx.recyclerview.widget.DiffUtil
import com.example.newscentral.model.Article

class diffUtil(private val oldList: ArrayList<Article>, private val newList: List<Article>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.count()
    }

    override fun getNewListSize(): Int {
        return newList.count()
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].url != newList[newItemPosition].url -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            else -> {
                true
            }
        }
    }
}