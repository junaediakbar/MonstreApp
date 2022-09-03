package com.monstre.monstreapp.ui.detailarticle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.monstre.monstreapp.data.remote.response.ArticleItem
import com.monstre.monstreapp.databinding.FragmentArticleBinding


typealias OnArticleClick = (article: ArticleItem) -> Unit
class ArticleFragment : Fragment() {


    private var binding: FragmentArticleBinding? = null

    var onArticleClick: OnArticleClick? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        val article = (arguments?.getParcelable<ArticleItem>(EXTRA_ARTICLE) as ArticleItem)
        setItemDetails(article)
        setBackButton()

        return binding?.root
    }

    private fun setItemDetails(article: ArticleItem){
        binding?.apply {
            tvArticleContent.text = article.desc
            tvArticleTitle.text = article.title
        }
    }

    private fun setBackButton() {
        binding?.ivBtnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object{
        const val EXTRA_ARTICLE = "extra_article"
    }
}