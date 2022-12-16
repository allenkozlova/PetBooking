package com.example.petbooking.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petbooking.domain.models.sitters.SitterModel
import com.example.petbooking.domain.repositories.sitters.MainRepository
import com.example.petbooking.errors.BadDataResponseException
import com.example.petbooking.presentation.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel (private val mainRepository: MainRepository) : ViewModel() {

    private var mStateSitters = MutableStateFlow<Resource<List<SitterModel>>>(Resource.Loading())
    val stateSitters get() = mStateSitters.asStateFlow()

    init {
        loadSitters()
    }

    private fun loadSitters() {
        mStateSitters.value = Resource.Loading()
        viewModelScope.launch {
            delay(5000L)
            repeat(5) {
                try {
                    val sitters = mainRepository.getSitters()
                    mStateSitters.value = Resource.Success(sitters)
                } catch (e: BadDataResponseException) {
                    mStateSitters.value = Resource.Error(e.message)
                }
            }
            delay(5000L)
        }

    }
}