package com.monstre.monstreapp.ui.signup

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.FragmentSignUpBinding
import com.monstre.monstreapp.ui.customview.EditTextWithValidation


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
            etEmail.setValidationCallback(object : EditTextWithValidation.InputValidation {
                override val errorMessage: String
                    get() = getString(R.string.email_validation_message)

                override fun validate(input: String) = input.isNotEmpty()
                        && Patterns.EMAIL_ADDRESS.matcher(input).matches()
            })


            etName.setValidationCallback(object : EditTextWithValidation.InputValidation {
                override val errorMessage: String
                    get() = getString(R.string.name_validation_message)

                override fun validate(input: String) = input.isNotEmpty()
            })

            etPassword.setValidationCallback(object : EditTextWithValidation.InputValidation {
                override val errorMessage: String
                    get() = getString(R.string.password_validation_message)

                override fun validate(input: String) = input.length >= 6
            })
        }
    }

    companion object
}