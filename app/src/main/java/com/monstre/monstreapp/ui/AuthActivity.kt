package com.monstre.monstreapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.databinding.ActivityAuthBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewModel: AuthViewModel
    private lateinit var pref: DataStore<Preferences>
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        pref = this.dataStore
        setContentView(binding.root)
        setupViewModel()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_auth) as NavHostFragment
        navController = navHostFragment.navController
        AppBarConfiguration(navController.graph)

        viewModel.user.observe(
            this@AuthActivity
        ) {
//TODO: add it later && it.selectedMbti.isNotEmpty()
            if(it.token.isNotEmpty()&& it.selectedMbti!=""){
                val intent = Intent (this@AuthActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val startingPoint =
                    if (it.token.isNotEmpty()) (R.id.nav_device) else (R.id.nav_login)
                val graphInflater = navController.navInflater
                val navGraph = graphInflater.inflate(R.navigation.auth_navigation)
                navGraph.setStartDestination(startingPoint)
                navController.graph = navGraph
            }

        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(SharedPreference.getInstance(pref), this)
        )[AuthViewModel::class.java]
    }
}