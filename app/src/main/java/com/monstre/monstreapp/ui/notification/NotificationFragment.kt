package com.monstre.monstreapp.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstre.monstreapp.databinding.FragmentNotificationBinding
import com.monstre.monstreapp.ui.adapter.NotificationAdapter
import com.monstre.monstreapp.utils.notificationList

class NotificationFragment : Fragment() {

    private var binding: FragmentNotificationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        setRecyclerView()
        setBackButton()
        return binding?.root
    }

    private fun setBackButton() {
        binding?.ivBtnBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setRecyclerView() {
        val setLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.apply {
            rvNotification.apply {
                layoutManager = setLayoutManager
                adapter = NotificationAdapter(notificationList)
            }
        }
    }

}