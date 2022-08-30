package com.monstre.monstreapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.databinding.ItemSuggestionBinding
import com.monstre.monstreapp.domain.model.Suggestion


class SuggestionAdapter(
    private val suggestionList: ArrayList<Suggestion>,
) :
    RecyclerView.Adapter<SuggestionAdapter.SuggestionHolder>() {


    override fun getItemCount(): Int = suggestionList.size

    class SuggestionHolder(itemBinding: ItemSuggestionBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var imgView = itemBinding.ivSuggestion
        var tvTitle = itemBinding.tvSuggestion
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionHolder {
        val itemBinding =
            ItemSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuggestionHolder(itemBinding)
    }

    private  var previousPosition: Int = -1

    override fun onBindViewHolder(holder: SuggestionHolder, position: Int) {
        val suggestionData: Suggestion = suggestionList[position]
        initialbind(suggestionData , holder, position)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    private fun initialbind(suggestionData : Suggestion, holder :SuggestionHolder, position :Int){
        holder.apply {
            imgView.setImageResource(suggestionData.image)
            tvTitle.text = suggestionData.description
        }
    }
}