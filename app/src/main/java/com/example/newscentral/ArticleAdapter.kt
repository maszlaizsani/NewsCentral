package com.example.newscentral

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newscentral.model.Article

class ArticleAdapter(myCtx: Context?, val articles: ArrayList<Article>): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView = itemView.findViewById(R.id.articleTitle)
        var itemText: TextView = itemView.findViewById(R.id.articleText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.article_list_element,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.itemTitle.text = article.title
        holder.itemText.text = article.content
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}