package com.example.petbooking.domain.repositories

import com.example.petbooking.domain.models.SitterModel

interface MainRepository {

    suspend fun getSitters(): List<SitterModel>
}