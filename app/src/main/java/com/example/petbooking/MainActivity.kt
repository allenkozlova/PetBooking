package com.example.petbooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.petbooking.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation(binding)
    }

    private fun initNavigation(binding: ActivityMainBinding) {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_mainFragment,
                R.id.nav_orderFragment,
                R.id.nav_favoritesFragment,
                R.id.nav_profileFragment
            )
        )

        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}