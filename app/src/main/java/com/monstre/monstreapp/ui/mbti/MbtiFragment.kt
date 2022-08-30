package com.monstre.monstreapp.ui.mbti


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.monstre.monstreapp.R
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

        binding?.apply {
            btnNext.setOnClickListener {
                val intent = Intent (requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }

        return binding?.root
    }

    private fun setRecyclerView() {
        val setLayoutManager = GridLayoutManager(requireContext(), 2)

        binding?.apply {
            rvMbtiList.apply {
                layoutManager = setLayoutManager
                adapter = MbtiAdapter(mbtiList)
            }.addItemDecoration(
                MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.recylerViewMargin),2)
            )

        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(SharedPreference.getInstance(pref), requireContext())
        )[MbtiViewModel::class.java]
    }
}