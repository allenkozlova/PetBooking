package com.example.petbooking.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petbooking.domain.models.requests.RequestModel
import com.example.petbooking.domain.repositories.requests.RequestsRepository
import com.example.petbooking.presentation.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActiveRequestsViewModel (private val requestsRepository: RequestsRepository) : ViewModel() {

    private var mStateActiveRequests = MutableStateFlow<Resource<List<RequestModel>>>(Resource.Loading())
    val stateActiveRequests get() = mStateActiveRequests.asStateFlow()

    init {
        subscribeToActiveRequestsSource()
    }

    private fun subscribeToActiveRequestsSource() {
        viewModelScope.launch(Dispatchers.IO) {
            mStateActiveRequests.emit(Resource.Loading())
            requestsRepository.getActiveRequests().collect {
                try {
                    mStateActiveRequests.emit(Resource.Success(it))
                } catch (e: Throwable) {
                    mStateActiveRequests.emit(Resource.Error("Ошибка"))
                }
            }
        }
    }
}