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
import com.example.newscentral.ArticleAdapter
import com.example.newscentral.R
import com.example.newscentral.databinding.FragmentHomeBinding
import com.example.newscentral.model.Article
import com.example.newscentral.service.NewsApi
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var articles: ArrayList<Article> = ArrayList()
        lifecycleScope.launch {
            articles = NewsApi.newsAPIService.getProperties().articles as ArrayList<Article>
            articles[0].isHeader = true // article has no id so this is how I separated the header
            val rView = view.findViewById<RecyclerView>(R.id.articleRecyclerView)
            rView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            rView.adapter = ArticleAdapter(context, articles) }
        println(articles.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}