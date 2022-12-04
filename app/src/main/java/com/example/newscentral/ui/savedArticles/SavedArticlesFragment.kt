package com.example.newscentral.ui.savedArticles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newscentral.database.SavedArticleDatabase
import com.example.newscentral.databinding.FragmentSavedArticlesBinding

class SavedArticlesFragment : Fragment() {

    private var _binding: FragmentSavedArticlesBinding? = null

    private val binding get() = _binding!!
    private val myAdapter: SavedArticlesAdapter = SavedArticlesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedArticlesBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = SavedArticleDatabase.getInstance(application).savedArticleDatabaseDao

        val viewModelFactory = SavedArticlesViewModelFactory(dataSource, application)
        val savedArticlesViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(SavedArticlesViewModel::class.java)

        val recycler = binding.savedArticlesRecycler

        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = myAdapter

        savedArticlesViewModel.getSavedArticles().observe(viewLifecycleOwner) {
            it?.let {
                myAdapter.setData(it)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}