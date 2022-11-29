package com.example.newscentral

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newscentral.model.Article


class ArticleAdapter(private var articles: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.articleTitle)
        var itemText: TextView = itemView.findViewById(R.id.articleText)
        var itemImage: ImageView = itemView.findViewById(R.id.articleImage)
        var itemSave: ImageView = itemView.findViewById(R.id.saveArticleIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.itemTitle.text = article.title
        holder.itemText.text = article.content
        holder.itemSave.setOnClickListener { /* TODO saveArticle function in database*/ }

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

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setData(newArticleList: ArrayList<Article>, swipeRefreshLayout: SwipeRefreshLayout, ) {
        val diffUtil = diffUtil(articles, newArticleList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        articles = newArticleList
        diffResults.dispatchUpdatesTo(this)
        swipeRefreshLayout.isRefreshing = false
    }
}