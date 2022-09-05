package com.monstre.monstreapp.ui.mbti


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.GridLayoutManager
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.Result
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.databinding.FragmentMbtiBinding
import com.monstre.monstreapp.ui.MainActivity
import com.monstre.monstreapp.ui.ViewModelFactory
import com.monstre.monstreapp.ui.adapter.MbtiAdapter
import com.monstre.monstreapp.utils.MarginItemDecoration
import com.monstre.monstreapp.utils.mbtiList

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MbtiFragment : Fragment() {

    private var binding: FragmentMbtiBinding? = null
    private lateinit var viewModel: MbtiViewModel
    private lateinit var pref: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMbtiBinding.inflate(inflater, container, false)
        pref = requireContext().dataStore
        setupViewModel()
        setRecyclerView()



        viewModel.user.observe(viewLifecycleOwner) { user ->
            viewModel.selectedMbti.observe(viewLifecycleOwner) { mbti ->

                binding?.apply {
                    btnNext.setOnClickListener {
                        if (mbti == "") {
                            Toast.makeText(
                                activity,
                                "Please input a personality",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        if (user.token.isNotEmpty() && mbti != "") {
                            Log.e("token =========", user.token)
                            viewModel.updateMbti(user.token, mbti.uppercase())
                                .observe(viewLifecycleOwner) { result ->
                                    if (result != null) {
                                        when (result) {
                                            is Result.Loading -> {
                                                showLoading(true)
                                            }
                                            is Result.Success -> {
                                                showLoading(false)
                                                val intent = Intent(
                                                    requireActivity(),
                                                    MainActivity::class.java
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

    private fun setRecyclerView() {
        val setLayoutManager = GridLayoutManager(requireContext(), 2)

        binding?.apply {
            rvMbtiList.apply {
                layoutManager = setLayoutManager
                adapter = MbtiAdapter(mbtiList, viewModel)
            }.addItemDecoration(
                MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.recylerViewMargin), 2)
            )

        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(SharedPreference.getInstance(pref), requireContext())
        )[MbtiViewModel::class.java]
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