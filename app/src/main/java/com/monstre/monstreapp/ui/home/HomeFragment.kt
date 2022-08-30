package com.monstre.monstreapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.FragmentDeviceBinding
import com.monstre.monstreapp.databinding.FragmentHomeBinding
import com.monstre.monstreapp.ui.adapter.DeviceAdapter
import com.monstre.monstreapp.ui.adapter.StressAdapter
import com.monstre.monstreapp.ui.adapter.SuggestionAdapter
import com.monstre.monstreapp.utils.deviceList
import com.monstre.monstreapp.utils.stressList
import com.monstre.monstreapp.utils.suggestionList

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val setLayoutManagerHorizontal  = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val setLayoutManagerVertical  = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.apply {
            rvStress.apply {
                setHasFixedSize(true)
                layoutManager = setLayoutManagerHorizontal
                adapter = StressAdapter(stressList)
            }
            rvSuggestion.apply {
                setHasFixedSize(true)
                layoutManager = setLayoutManagerVertical
                adapter = SuggestionAdapter(suggestionList)
            }
        }
        return binding?.root
    }

}