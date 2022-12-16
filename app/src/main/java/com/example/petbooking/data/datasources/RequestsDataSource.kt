package com.example.petbooking.data.datasources

import com.example.petbooking.data.databases.PetBookingDataBase
import com.example.petbooking.data.databases.RequestsEntity

class RequestsDataSource (private val db: PetBookingDataBase) {

    suspend fun updateRequests(requests: List<RequestsEntity>) {
        db.requestsDao().insert(requests)
    }

    fun getActiveRequests() = db.requestsDao().getActiveRequests()

    fun getCancelRequests() = db.requestsDao().getCancelRequests()
}