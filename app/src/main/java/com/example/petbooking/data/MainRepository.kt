package com.example.petbooking.data

import javax.inject.Inject

class MainRepository @Inject constructor (private val apiService: ApiService) {
    suspend fun getUsers(): List<ApiUser> = apiService.getUsers()
}