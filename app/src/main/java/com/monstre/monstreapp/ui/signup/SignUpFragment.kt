package com.monstre.monstreapp.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.FragmentLoginBinding
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
        binding?.tvLogin?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object
}