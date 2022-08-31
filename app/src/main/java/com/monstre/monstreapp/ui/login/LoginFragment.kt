package com.monstre.monstreapp.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.FragmentLoginBinding
import com.monstre.monstreapp.ui.customview.EditTextWithValidation

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply{
            tvSignup.setOnClickListener {
                findNavController().navigate(
                    R.id.action_loginFragment_to_registerFragment,
                    null,
                    null
                )
            }
            btnLogin.setOnClickListener {
                findNavController().navigate(
                    R.id.action_nav_login_to_deviceFragment,
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

            etPassword.setValidationCallback(object : EditTextWithValidation.InputValidation {
                override val errorMessage: String
                    get() = getString(R.string.password_validation_message)

                override fun validate(input: String) = input.length >= 6
            })
        }
    }


    fun TextInputEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
    companion object
}