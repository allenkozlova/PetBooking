package com.example.petbooking.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petbooking.domain.repositories.MainRepository
import com.example.petbooking.presentation.viewmodels.MainScreenViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ViewModelFactory @AssistedInject constructor (
    @Assisted("id") private val id: Int,
    private val mainRepository: MainRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            return MainScreenViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("id") id: Int): ViewModelFactory
    }

}