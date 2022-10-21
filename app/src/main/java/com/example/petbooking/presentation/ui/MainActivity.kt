package com.example.petbooking.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.petbooking.R
import com.example.petbooking.presentation.factories.ViewModelFactory
import com.example.petbooking.appComponent
import com.example.petbooking.presentation.viewmodels.BaseViewModel
import com.example.petbooking.presentation.utils.Status
import javax.inject.Inject

//Почитать про AndroidInjector, внедрить его
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.Factory

    private val id: Int = 0

    private val baseViewModel: BaseViewModel by viewModels {
        viewModelFactory.create(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)

        setupObserver()
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
}