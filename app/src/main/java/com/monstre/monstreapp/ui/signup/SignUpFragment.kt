package com.monstre.monstreapp.ui.signup

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.Result
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.data.remote.response.RegisterResponse
import com.monstre.monstreapp.databinding.FragmentSignUpBinding
import com.monstre.monstreapp.ui.ViewModelFactory
import com.monstre.monstreapp.ui.customview.EditTextWithValidation

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SignUpFragment : Fragment() {

    private var binding: FragmentSignUpBinding? = null
    private lateinit var viewModel: SignUpViewModel
    private lateinit var pref: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        pref = requireContext().dataStore
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding?.apply {
            tvLogin.setOnClickListener {
                findNavController().popBackStack()
            }
            btnLogin.setOnClickListener {
                val name = etName.text.toString()
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                viewModel.signup(name,email, password).observe(viewLifecycleOwner) {
                    signUpObserver(it)
                }

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

    private fun signUpObserver(result: Result<RegisterResponse>) {
        when (result) {
            is Result.Loading -> {
                showLoading(true)
            }
            is Result.Success -> {
                findNavController().popBackStack()
                showLoading(false)
            }
            is Result.Error -> {
                showLoading(false)
                showMessage(getString(R.string.something_wrong))
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showMessage(message: String) {
        if (message != "") {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(SharedPreference.getInstance(pref), requireContext())
        )[SignUpViewModel::class.java]
    }
    companion object
}