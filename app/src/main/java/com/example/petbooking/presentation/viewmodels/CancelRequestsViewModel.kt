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

class CancelRequestsViewModel (private val requestsRepository: RequestsRepository) : ViewModel() {

    private var mStateCancelRequests = MutableStateFlow<Resource<List<RequestModel>>>(Resource.Loading())
    val stateCancelRequests get() = mStateCancelRequests.asStateFlow()

    init {
        subscribeToActiveRequestsSource()
    }

    private fun subscribeToActiveRequestsSource() {
        viewModelScope.launch(Dispatchers.IO) {
            mStateCancelRequests.emit(Resource.Loading())
            requestsRepository.getCancelRequests().collect {
                try {
                    mStateCancelRequests.emit(Resource.Success(it))
                } catch (e: Throwable) {
                    mStateCancelRequests.emit(Resource.Error("Ошибка"))
                }
            }
        }
    }
}