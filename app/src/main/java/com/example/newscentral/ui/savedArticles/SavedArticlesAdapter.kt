package com.example.newscentral.ui.savedArticles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newscentral.R
import com.example.newscentral.database.SavedArticleEntity


class SavedArticlesAdapter() :
    RecyclerView.Adapter<SavedArticlesAdapter.ViewHolder>() {
    private var articles: ArrayList<SavedArticleEntity> = ArrayList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.articleTitle)
        var itemText: TextView = itemView.findViewById(R.id.articleText)
        var itemImage: ImageView = itemView.findViewById(R.id.articleImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: SavedArticleEntity = articles[position]
        holder.itemTitle.text = article.title
        holder.itemText.text = article.content

        val imgUrl = article.urlToImage
        val imgUri = imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(holder.itemImage.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.image)
            )
            .into(holder.itemImage)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setData(list: List<SavedArticleEntity>?) {
        articles = list as ArrayList<SavedArticleEntity>
        notifyDataSetChanged()
    }
}