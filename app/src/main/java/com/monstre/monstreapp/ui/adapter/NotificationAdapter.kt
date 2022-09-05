package com.monstre.monstreapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monstre.monstreapp.databinding.ItemNotificationBinding
import com.monstre.monstreapp.domain.model.Notification

class NotificationAdapter(
    private val notificationList: ArrayList<Notification>,
) :
    RecyclerView.Adapter<NotificationAdapter.NotificationHolder>() {


    override fun getItemCount(): Int = notificationList.size

    class NotificationHolder(itemBinding: ItemNotificationBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        var tvSlug = itemBinding.tvNotifSlug
        var tvTitle = itemBinding.tvNotifTitle
        var tvContent = itemBinding.tvNotifContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val itemBinding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationHolder(itemBinding)
    }

    private  var previousPosition: Int = -1

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        val notificationData: Notification = notificationList[position]
        initialbind(notificationData , holder, position)
    }

    @SuppressLint("ResourceAsColor", "NotifyDataSetChanged")
    private fun initialbind(notificationData : Notification, holder :NotificationHolder, position :Int){
        holder.apply {
            tvSlug.text = notificationData.slug
            tvTitle.text = notificationData.title
            tvContent.text = notificationData.content
        }
    }
}