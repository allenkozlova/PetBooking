package com.example.petbooking.domain.repositories.sitters

import com.example.petbooking.domain.models.sitters.SitterModel

interface MainRepository {

    suspend fun getSitters(): List<SitterModel>
}