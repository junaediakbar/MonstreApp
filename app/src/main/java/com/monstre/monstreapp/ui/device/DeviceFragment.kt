package com.monstre.monstreapp.ui.device

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.FragmentDeviceBinding
import com.monstre.monstreapp.databinding.FragmentLoginBinding
import com.monstre.monstreapp.ui.adapter.DeviceAdapter
import com.monstre.monstreapp.utils.deviceList


class DeviceFragment : Fragment() {

    private var binding: FragmentDeviceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeviceBinding.inflate(inflater, container, false)

        val setLayoutManager  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.apply {
            rvDeviceList.apply {
                setHasFixedSize(true)
                layoutManager = setLayoutManager
                adapter = DeviceAdapter(deviceList)
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    R.id.action_nav_device_to_mbtiFragment,
                    null,
                    null
                )
            }
        }

        return binding?.root
    }

}