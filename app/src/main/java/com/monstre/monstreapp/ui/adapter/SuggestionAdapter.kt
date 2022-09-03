package com.monstre.monstreapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.data.remote.response.ArticleItem
import com.monstre.monstreapp.databinding.ItemSuggestionBinding
import com.monstre.monstreapp.domain.model.Suggestion


class SuggestionAdapter(
    private val suggestionList: ArrayList<ArticleItem>,
) :
    RecyclerView.Adapter<SuggestionAdapter.SuggestionHolder>() {

    var onItemClick: ((ArticleItem) -> Unit)? = null

    override fun getItemCount(): Int = suggestionList.size

   inner class SuggestionHolder(itemBinding: ItemSuggestionBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var imgView = itemBinding.ivSuggestion
        var tvTitle = itemBinding.tvSuggestion
        var btnDetail = itemBinding.tvSuggesstionDetail

        init {
            btnDetail.setOnClickListener {
                onItemClick?.invoke(suggestionList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionHolder {
        val itemBinding =
            ItemSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestionHolder(itemBinding)
    }

    private  var previousPosition: Int = -1

    override fun onBindViewHolder(holder: SuggestionHolder, position: Int) {
        val suggestionData: ArticleItem = suggestionList[position]
        initialbind(suggestionData , holder, position)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    private fun initialbind(suggestionData : ArticleItem, holder :SuggestionHolder, position :Int){
        holder.apply {
            tvTitle.text = suggestionData.title

        }
    }
}