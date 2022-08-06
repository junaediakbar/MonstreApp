package com.monstre.monstreapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.monstre.monstreapp.R
import com.monstre.monstreapp.databinding.ActivityMainBinding
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = binding.navParentView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment

        navView.setupWithNavController(navHostFragment.navController)

        navView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navHostFragment.navController)
        }

        navHostFragment.navController.addOnDestinationChangedListener { _, dest, _ ->
            navView.visibility = if (
                dest.id == R.id.nav_home
                || dest.id == R.id.nav_profile
                || dest.id == R.id.nav_notification
                || dest.id == R.id.nav_history
            ) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }


}