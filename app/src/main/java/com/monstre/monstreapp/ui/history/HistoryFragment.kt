package com.monstre.monstreapp.ui.history

import android.content.Context
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.Result
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.databinding.FragmentHistoryBinding
import com.monstre.monstreapp.ui.ViewModelFactory
import com.monstre.monstreapp.ui.adapter.HistoryAdapter

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class HistoryFragment : Fragment() {
    private var binding: FragmentHistoryBinding? = null
    private lateinit var viewModel: HistoryViewModel
    private lateinit var pref: DataStore<Preferences>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        pref = requireContext().dataStore
        setupViewModel()
        setBackButton()
        val setLayoutManagerVertical =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.apply {
            rvHistory.apply {
                setHasFixedSize(true)
                layoutManager = setLayoutManagerVertical
                viewModel.user.observe(viewLifecycleOwner) { user ->
                    if (user.token.isNotEmpty()) {
                        viewModel.getFullYearHistory(user.token)
                            .observe(viewLifecycleOwner) { result ->
                                if (result != null) {
                                    when (result) {
                                        is Result.Loading -> {
                                            showLoading(true)
                                        }
                                        is Result.Success -> {
                                            showLoading(false)
                                            adapter = HistoryAdapter(result.data.data)
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
        return binding?.root
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
        )[HistoryViewModel::class.java]
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showMessage(message: String) {
        if (message != "") {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}