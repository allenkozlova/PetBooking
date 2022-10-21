package com.example.petbooking.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petbooking.data.ApiUser
import com.example.petbooking.data.MainRepository
import com.example.petbooking.presentation.utils.Resource
import kotlinx.coroutines.launch

// id - условно мееяющийся параметр (сделан для примера внедрения AssistedInject
class BaseViewModel (private val id: Int, private val mainRepository: MainRepository) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromApi = mainRepository.getUsers()
                users.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}