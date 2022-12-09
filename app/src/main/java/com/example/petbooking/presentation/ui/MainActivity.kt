package com.example.petbooking.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.petbooking.R
import com.example.petbooking.databinding.ActivityMainBinding
import com.example.petbooking.presentation.factories.ViewModelFactory
import com.example.petbooking.presentation.utils.Status
import com.example.petbooking.presentation.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.Factory

    private lateinit var binding: ActivityMainBinding

    private val id: Int = 0

    private val baseViewModel: MainViewModel by viewModels {
        viewModelFactory.create(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.main,
            R.id.requests,
            R.id.favorites,
            R.id.profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)

        binding.tv.setOnClickListener {
            baseViewModel.fetchCats()
        }
        setupObserver()
        setupObserver1()
    }


    private fun setupObserver() {
        baseViewModel.getUsers().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    val t = 1
                }
                Status.LOADING -> {
                    val t = 1
                }
                Status.ERROR -> {
                    //Handle Error
                    val t = 1
                }
            }
        }
    }

    private fun setupObserver1() {
        baseViewModel.getCats().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    val t = 1
                }
                Status.LOADING -> {
                    val t = 1
                }
                Status.ERROR -> {
                    //Handle Error
                    val t = 1
                }
            }
        }
    }
}