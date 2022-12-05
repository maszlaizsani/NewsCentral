package com.example.newscentral.ui.detailedView

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newscentral.R
import com.example.newscentral.databinding.FragmentDetailedViewBinding


class DetailedFragment() : Fragment() {

    private var _binding: FragmentDetailedViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedViewBinding.inflate(inflater, container, false)

        val arguments = DetailedFragmentArgs.fromBundle(requireArguments())

        binding.titleText.text = arguments.selectedArticle.title
        Glide.with(this)
            .load(arguments.selectedArticle.urlToImage)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.image)
            )
            .into(binding.image)
        binding.content.text = arguments.selectedArticle.content
        binding.openButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(arguments.selectedArticle.url))
            startActivity(browserIntent) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}