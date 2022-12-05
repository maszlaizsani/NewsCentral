package com.example.newscentral.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newscentral.databinding.FragmentHomeBinding
import com.example.newscentral.APImodel.Article
import com.example.newscentral.service.NewsApi
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var articles: ArrayList<Article> = ArrayList()
    private lateinit var myAdapter : ArticleAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        swipeRefreshLayout = binding.swipeRefresh

        binding.articleRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        myAdapter = ArticleAdapter(articles, ArticleAdapter.ArticleListener {
            homeViewModel.displayArticleDetails(it)})
        binding.articleRecyclerView.adapter = myAdapter

        homeViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(HomeFragmentDirections.openDetailFragment(it))
                homeViewModel.displayArticleDetailsComplete()
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                articles =
                    NewsApi.newsAPIService.getHeadlinesInHungary().articles as ArrayList<Article>
                myAdapter.setData(articles, swipeRefreshLayout)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            articles = NewsApi.newsAPIService.getHeadlinesInHungary().articles as ArrayList<Article>
            myAdapter.setData(articles, swipeRefreshLayout)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}