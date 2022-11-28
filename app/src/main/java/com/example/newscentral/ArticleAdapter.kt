package com.example.newscentral

import android.content.Context
import android.icu.util.UniversalTimeScale.from
import android.view.LayoutInflater
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newscentral.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class ArticleAdapter(myCtx: Context?, val articles: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.articleTitle)
        var itemText: TextView = itemView.findViewById(R.id.articleText)
        var itemImage: ImageView = itemView.findViewById(R.id.articleImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_element, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.itemTitle.text = article.title
        holder.itemText.text = article.content

        val imgUrl = article.urlToImage
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(holder.itemImage.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.image)
            )
            .into(holder.itemImage)
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    sealed class DataItem {
        abstract val isHeader: Boolean

        data class ArticleItem(val article: Article) : DataItem() {
            override val isHeader = article.isHeader
        }

        object Header : DataItem() {
            override val isHeader = true
        }
    }
}