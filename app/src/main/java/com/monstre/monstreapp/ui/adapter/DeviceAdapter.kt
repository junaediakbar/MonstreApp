package com.monstre.monstreapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.data.remote.response.ArticleItem
import com.monstre.monstreapp.databinding.ItemDeviceBinding
import com.monstre.monstreapp.domain.model.Device

class DeviceAdapter(private val deviceList: ArrayList<Device>) : RecyclerView.Adapter<DeviceAdapter.DeviceHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    var onItemClick: ((Device) -> Unit)? = null
    override fun getItemCount(): Int = deviceList.size

    inner class DeviceHolder( itemBinding: ItemDeviceBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var imgPhoto = itemBinding.ivDevice
        var tvTitle = itemBinding.tvDevice
        var btnDetail = itemBinding.ivDetail
        var checkbox = itemBinding.checkBox

        init {
            btnDetail.setOnClickListener {
                deviceList[adapterPosition].connected = !checkbox.isChecked
                checkbox.isChecked =  !checkbox.isChecked
                onItemClick?.invoke(deviceList[adapterPosition])

            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceHolder {
        val itemBinding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DeviceHolder, position: Int) {
        val deviceData: Device = deviceList[position]
        holder.apply {
            tvTitle.text = deviceData.name
            imgPhoto.setImageResource(deviceData.image)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Device)
    }
}