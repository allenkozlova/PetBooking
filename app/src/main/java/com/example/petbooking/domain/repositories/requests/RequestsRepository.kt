package com.example.petbooking.domain.repositories.requests

import com.example.petbooking.domain.models.requests.RequestModel
import kotlinx.coroutines.flow.Flow

interface RequestsRepository {

    suspend fun loadRequests()

    suspend fun getActiveRequests(): Flow<List<RequestModel>>

    suspend fun getCancelRequests(): Flow<List<RequestModel>>
}