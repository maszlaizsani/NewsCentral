package com.example.newscentral.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newscentral.APImodel.Article
import com.example.newscentral.APImodel.Article.convertApiModelToEntity
import com.example.newscentral.R
import com.example.newscentral.database.SavedArticleDatabase
import com.example.newscentral.diffUtil
import com.example.newscentral.ui.detailedView.DetailedFragment
import com.github.satoshun.coroutine.autodispose.view.autoDisposeScope
import kotlinx.coroutines.launch

class ArticleAdapter(private var articles: ArrayList<Article>, private val articleListener: ArticleListener) :
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
        holder.itemView.setOnClickListener { articleListener.onClick(articles[position]) }
        holder.itemSave.setOnClickListener {
            holder.itemView.autoDisposeScope.launch {
                val savedArticle = convertApiModelToEntity(articles[position])
                val dataBase =
                    SavedArticleDatabase.getInstance(holder.itemSave.context).savedArticleDatabaseDao
                if (dataBase.getNextId() != null) savedArticle.articleId = dataBase.getNextId()
                dataBase.insert(savedArticle)
            }
            Toast.makeText(holder.itemSave.context, "Article saved!", Toast.LENGTH_SHORT).show()
        }

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

        holder.itemImage.setOnClickListener {
            val action = HomeFragmentDirections.openDetailFragment(articles[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleListener(val clickListener: (article: Article) -> Unit) {
        fun onClick(article: Article) = clickListener(article)
    }

    fun setData(newArticleList: ArrayList<Article>, swipeRefreshLayout: SwipeRefreshLayout) {
        val diffUtil = diffUtil(articles, newArticleList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        articles = newArticleList
        diffResults.dispatchUpdatesTo(this)
        swipeRefreshLayout.isRefreshing = false
    }
}