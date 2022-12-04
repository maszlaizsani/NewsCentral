package com.example.newscentral.ui.detailedView

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newscentral.APImodel.Article
import com.example.newscentral.R
import com.example.newscentral.database.SavedArticleDatabase
import com.example.newscentral.databinding.FragmentDetailedViewBinding
import com.example.newscentral.ui.home.HomeViewModel


class DetailedFragment(val article: Article) : Fragment() {

    private var _binding: FragmentDetailedViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedViewBinding.inflate(inflater, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = SavedArticleDatabase.getInstance(application).savedArticleDatabaseDao

        val viewModelFactory = DetailedViewModelFactory(article)
        val detailedViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.titleText.text = article.title
        Glide.with(this)
            .load(article.urlToImage)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.image)
            )
            .into(binding.image)
        binding.content.text = article.content
        binding.openButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(browserIntent) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}