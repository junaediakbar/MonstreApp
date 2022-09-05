package com.monstre.monstreapp.ui.profile

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.Result
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.data.remote.response.UserResponse
import com.monstre.monstreapp.databinding.FragmentProfileBinding
import com.monstre.monstreapp.ui.AuthActivity
import com.monstre.monstreapp.ui.ViewModelFactory
import kotlinx.coroutines.launch


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private lateinit var viewModel: ProfileViewModel
    private lateinit var pref: DataStore<Preferences>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        pref = requireContext().dataStore
        setBackButton()
        setupViewModel()

        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user.token.isNotEmpty()) {
//              Log.e("token : =======", it.token)
                viewModel.getProfile(user.token).observe(viewLifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                showLoading(true)
                            }
                            is Result.Success -> {
                                showLoading(false)
                                setUserData(result.data)
                            }
                            is Result.Error -> {
                                showLoading(false)
                                showMessage(getString(R.string.something_wrong))
                            }
                        }
                    }
                }
                binding?.apply {
                    ivLogout.setOnClickListener {
                        lifecycleScope.launch {
                            viewModel.logout(user.token).observe(viewLifecycleOwner) { result ->
                                if (result != null) {
                                    when (result) {
                                        is Result.Loading -> {
                                            showLoading(true)
                                        }
                                        is Result.Success -> {
                                            showLoading(false)
                                            val intent = Intent(
                                                requireActivity(),
                                                AuthActivity::class.java
                                            )
                                            startActivity(intent)
                                            activity?.finish()
                                        }
                                        is Result.Error -> {
                                            showLoading(false)
                                            showMessage(getString(R.string.something_wrong))
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }


        return binding?.root
    }


    private fun setUserData(user: UserResponse) {
        binding?.apply {
            tvProfileEmail.text = user.email
            tvProfileName.text = user.name
            Glide.with(ivAvatar.context)
                .load("https://monstre-production.herokuapp.com/storage/images/avatars/${user.id}/${user.avatar}")
                .placeholder(R.drawable.img_profile_avatar)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(ivAvatar)
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

    private fun setBackButton() {
        binding?.ivBtnBack?.setOnClickListener {
        findNavController().popBackStack()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(SharedPreference.getInstance(pref), requireContext())
        )[ProfileViewModel::class.java]
    }


}