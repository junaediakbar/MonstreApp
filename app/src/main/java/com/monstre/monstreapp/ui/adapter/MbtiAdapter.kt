package com.monstre.monstreapp.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.ItemMbtiBinding
import com.monstre.monstreapp.domain.model.Mbti
import com.monstre.monstreapp.ui.mbti.MbtiViewModel
import com.monstre.monstreapp.utils.loadSvg


class MbtiAdapter(
    private val mbtiList: ArrayList<Mbti>,
    private val viewModel: MbtiViewModel
) :
    RecyclerView.Adapter<MbtiAdapter.MbtiHolder>() {

    init {
        mbtiList.map{
            it.isSelected =false
        }
    }
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

    fun getSelectedMbti(): String{
        return selectedMbti
    }

    private var previousPosition: Int = -1
    private var selectedMbti : String = ""


    override fun onBindViewHolder(holder: MbtiHolder, position: Int) {
        val mbtiData: Mbti = mbtiList[position]
        initialbind(mbtiData , holder, position)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    private fun initialbind(mbtiData : Mbti, holder :MbtiHolder, position :Int){
        holder.apply {
            tvTitle.text = mbtiData.name + "-A/T"
            imgPhoto.loadSvg(mbtiData.urlImage.toString())
            cardView.setBackgroundResource(if (mbtiData.isSelected) R.drawable.bg_litte_button_second else R.drawable.bg_button_white)
            itemView.setOnClickListener {
                mbtiData.isSelected = !mbtiData.isSelected
                selectedMbti = mbtiList[position].name + "-A/T"
                viewModel.updateStateMbti(selectedMbti)
                //  if (mModelList.filter { it.isSelected }.isEmpty()) previousPosition = -1
                notifyItemChanged(previousPosition)
                if (previousPosition != -1 && previousPosition != position) {
                    mbtiList[previousPosition].isSelected = false
                    previousPosition = position
                } else {
                    previousPosition = position
                    selectedMbti = mbtiList[position].name.toString()
                }
                notifyItemChanged(position)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Mbti)
    }

}