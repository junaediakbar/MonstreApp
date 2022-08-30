package com.monstre.monstreapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.databinding.ItemStressProgressBinding
import com.monstre.monstreapp.domain.model.StressLevel

class StressAdapter(
    private val stressList: ArrayList<StressLevel>,
) :
RecyclerView.Adapter<StressAdapter.StressHolder>() {


    override fun getItemCount(): Int = stressList.size

    class StressHolder(itemBinding: ItemStressProgressBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var tvday = itemBinding.tvStressDay
        var progress =itemBinding.progressBar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StressHolder {
        val itemBinding =
            ItemStressProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StressHolder(itemBinding)
    }

    private  var previousPosition: Int = -1


    override fun onBindViewHolder(holder: StressHolder, position: Int) {
        val stressData: StressLevel = stressList[position]
        initialbind(stressData , holder, position)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    private fun initialbind(stressData : StressLevel, holder :StressHolder, position :Int){
        holder.apply {
            tvday.text = stressData.time
            progress.progress = stressData.level
        }
    }
}