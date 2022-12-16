package com.example.petbooking.domain.models.requests

data class RequestModel(
    val id: Long,
    val requestDate: String,
    val pets: List<PetModel>,
    val dateFrom: String,
    val dateTo: String,
    val status: Int
) {
    data class PetModel(
        val id: Long,
        val name: String,
        val breed: String
    )
}