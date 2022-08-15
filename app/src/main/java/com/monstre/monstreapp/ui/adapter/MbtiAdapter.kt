package com.monstre.monstreapp.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.ItemMbtiBinding
import com.monstre.monstreapp.domain.Mbti
import com.monstre.monstreapp.utils.loadSvg


class MbtiAdapter(
    private val mbtiList: ArrayList<Mbti>,
) :
    RecyclerView.Adapter<MbtiAdapter.MbtiHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int = mbtiList.size

    class MbtiHolder(itemBinding: ItemMbtiBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var imgPhoto = itemBinding.imgMbti
        var tvTitle = itemBinding.tvMbtiName
        var cardView =itemBinding.cardMbti
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MbtiHolder {
        val itemBinding =
            ItemMbtiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MbtiHolder(itemBinding)
    }

    private  var previousPosition: Int = -1


    override fun onBindViewHolder(holder: MbtiHolder, position: Int) {
        val mbtiData: Mbti = mbtiList[position]
        initialbind(mbtiData , holder, position)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    private fun initialbind(mbtiData :Mbti, holder :MbtiHolder, position :Int){
        holder.apply {
            tvTitle.text = mbtiData.name
            imgPhoto.loadSvg(mbtiData.urlImage.toString())
            cardView.setBackgroundColor(if (mbtiData.isSelected) R.color.blue_500 else Color.WHITE)
            itemView.setOnClickListener {
                mbtiData.isSelected = !mbtiData.isSelected
                //  if (mModelList.filter { it.isSelected }.isEmpty()) previousPosition = -1
                notifyItemChanged(previousPosition)
                if (previousPosition != -1 && previousPosition != position) {
                    mbtiList[previousPosition].isSelected = false
                    previousPosition = position
                } else {
                    previousPosition = position
                }
                notifyItemChanged(position)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Mbti)
    }

}