package com.example.newscentral.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newscentral.ArticleAdapter
import com.example.newscentral.R
import com.example.newscentral.databinding.FragmentHomeBinding
import com.example.newscentral.model.Article
import com.example.newscentral.service.NewsApi
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    var articles: ArrayList<Article> = ArrayList()
    var myAdapter = ArticleAdapter(articles)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val swipeRefreshLayout : SwipeRefreshLayout = binding.swipeRefresh

        swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                articles = NewsApi.newsAPIService.getProperties().articles as ArrayList<Article>
                articles[0].isHeader = true // article has no id so this is how I separated the header
                myAdapter.setData(articles, swipeRefreshLayout)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            articles = NewsApi.newsAPIService.getProperties().articles as ArrayList<Article>
            articles[0].isHeader = true // article has no id so this is how I separated the header
            val rView = view.findViewById<RecyclerView>(R.id.articleRecyclerView)
            rView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            //myAdapter = ArticleAdapter(articles)
            rView.adapter = ArticleAdapter(articles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}