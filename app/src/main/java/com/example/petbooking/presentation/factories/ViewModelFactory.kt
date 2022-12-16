package com.example.petbooking.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petbooking.domain.repositories.requests.RequestsRepository
import com.example.petbooking.domain.repositories.sitters.MainRepository
import com.example.petbooking.presentation.viewmodels.ActiveRequestsViewModel
import com.example.petbooking.presentation.viewmodels.CancelRequestsViewModel
import com.example.petbooking.presentation.viewmodels.MainScreenViewModel
import com.example.petbooking.presentation.viewmodels.RequestsViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ViewModelFactory @AssistedInject constructor (
    @Assisted("id") private val id: Int,
    private val mainRepository: MainRepository,
    private val requestsRepository: RequestsRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            return MainScreenViewModel(mainRepository) as T
        } else if (modelClass.isAssignableFrom(ActiveRequestsViewModel::class.java)) {
            return ActiveRequestsViewModel(requestsRepository) as T
        } else if (modelClass.isAssignableFrom(RequestsViewModel::class.java)) {
            return RequestsViewModel(requestsRepository) as T
        } else if (modelClass.isAssignableFrom(CancelRequestsViewModel::class.java)) {
            return CancelRequestsViewModel(requestsRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("id") id: Int): ViewModelFactory
    }

}