package com.example.petbooking.domain.models.sitters

data class SitterModel(
    val id: Long,
    val sitterPhoto: String,
    val name: String,
    val city: String,
    val district: String,
    val mark: Int,
    val feedbacks: Int,
    val price: Double
)