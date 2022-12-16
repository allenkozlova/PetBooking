package com.example.petbooking.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petbooking.data.datasources.RequestsDataSource
import com.example.petbooking.domain.repositories.requests.RequestsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RequestsViewModel (private val requestsRepository: RequestsRepository) : ViewModel() {

    init {
        loadRequests()
    }

    private fun loadRequests() {
        viewModelScope.launch (Dispatchers.IO) {
            requestsRepository.loadRequests()
        }
    }
}