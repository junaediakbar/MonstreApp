package com.monstre.monstreapp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            tvLogin.setOnClickListener {
                findNavController().popBackStack()
            }
            btnLogin.setOnClickListener {
                findNavController().navigate(
                    R.id.action_nav_signup_to_deviceFragment,
                    null,
                    null
                )
            }
        }
    }

    companion object
}