package com.monstre.monstreapp.ui.device

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.databinding.FragmentDeviceBinding
import com.monstre.monstreapp.ui.ViewModelFactory
import com.monstre.monstreapp.ui.adapter.DeviceAdapter
import com.monstre.monstreapp.utils.deviceList


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DeviceFragment : Fragment() {

    private var binding: FragmentDeviceBinding? = null
    private lateinit var viewModel: DeviceViewModel
    private lateinit var pref: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeviceBinding.inflate(inflater, container, false)
        pref = requireContext().dataStore

        val setLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.apply {
            rvDeviceList.apply {
                setHasFixedSize(true)
                layoutManager = setLayoutManager
                val adapter = DeviceAdapter(deviceList)
                this.adapter = adapter
                adapter.onItemClick = {
                    if(it.connected == false){
                        showDisconnectedDialog()
                    }else{
                        showProfileDialog()
                    }

                }
            }
            btnNext.setOnClickListener {
                findNavController().navigate(
                    R.id.action_nav_device_to_mbtiFragment,
                    null,
                    null
                )
            }
            btnBack.setOnClickListener {
                viewModel.user.observe(viewLifecycleOwner){
                    if (it.token.isEmpty()){
                        findNavController().popBackStack()
                    }
                }

            }
        }

        return binding?.root
    }

    private fun showProfileDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_success)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
    }

    private fun showDisconnectedDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_failed)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(SharedPreference.getInstance(pref), requireContext())
        )[DeviceViewModel::class.java]
    }

}