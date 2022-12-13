package com.example.petbooking.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petbooking.domain.models.SitterModel
import com.example.petbooking.domain.repositories.MainRepository
import com.example.petbooking.errors.BadDataResponseException
import com.example.petbooking.presentation.ui.main_screen.view_states.SitterMainScreenHolderState
import com.example.petbooking.presentation.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.logging.Handler

class MainScreenViewModel (private val mainRepository: MainRepository) : ViewModel() {

    private var mStateSitters = MutableStateFlow<Resource<List<SitterMainScreenHolderState>>>(Resource.Loading())
    val stateSitters get() = mStateSitters.asStateFlow()

    init {
        loadSitters()
    }

    private fun loadSitters() {
        mStateSitters.value = Resource.Loading()
        viewModelScope.launch {
            repeat(5) {
                try {
                    val sitters = mainRepository.getSitters()
                    var sitterHolderStates: MutableList<SitterMainScreenHolderState> = mutableListOf()
                    sitters.forEach {
                        sitterHolderStates.add(SitterMainScreenHolderState(it))
                    }
                    mStateSitters.value = Resource.Success(sitterHolderStates)
                } catch (e: BadDataResponseException) {
                    mStateSitters.value = Resource.Error(e.message)
                }
            }
            delay(5000L)
        }

    }
}