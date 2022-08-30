package com.monstre.monstreapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.monstre.monstreapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setBackButton()
        return binding?.root
    }

    private fun setBackButton(){
        binding?.ivBtnBack?.setOnClickListener{
            findNavController().popBackStack()
        }
    }


}