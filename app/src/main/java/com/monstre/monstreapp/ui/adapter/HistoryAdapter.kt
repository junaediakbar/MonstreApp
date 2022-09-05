package com.monstre.monstreapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.remote.response.SaturationData
import com.monstre.monstreapp.databinding.ItemHistoryBinding
import com.monstre.monstreapp.domain.model.Mbti

class HistoryAdapter(
    private val saturationList: ArrayList<SaturationData>,
) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int = saturationList.size

    class HistoryHolder(itemBinding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var imgPhoto = itemBinding.ivStressLevel
        var tvStressLevel = itemBinding.tvStressLevel
        var tvStressNumber = itemBinding.tvStressNumber
        var tvStressSpO2Number = itemBinding.tvStressSpo2Number
        var tvStressBpmNumber = itemBinding.tvStressBpmNumber
        var tvStressDate = itemBinding.tvStressDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val itemBinding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryHolder(itemBinding)
    }

    private var previousPosition: Int = -1


    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val saturationData: SaturationData = saturationList[position]
        initialbind(saturationData, holder, position)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    private fun initialbind(
        saturationData: SaturationData,
        holder: HistoryHolder,
        position: Int
    ) {
        holder.apply {
            when (saturationData.desc) {
                "Calm" -> {
                    imgPhoto.setImageResource(R.drawable.img_emoji_smile)
                }
                "Relax" -> {
                    imgPhoto.setImageResource(R.drawable.img_emoji_relax)
                }
                "Anxious" -> {
                    imgPhoto.setImageResource(R.drawable.img_emoji_anxious)
                }
                "Stressed" -> {
                    imgPhoto.setImageResource(R.drawable.img_emoji_stress)
                }
            }
            tvStressLevel.text = saturationData.desc
            tvStressDate.text = saturationData.date
            tvStressBpmNumber.text = saturationData.bpm.toString()
            tvStressSpO2Number.text = saturationData.spo2.toString()
            tvStressNumber.text = "Lvl. " + saturationData.stressNumber.toString()
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Mbti)
    }

}