package com.example.petbooking.data.api_models.requests

import com.google.gson.annotations.SerializedName

data class RequestApiModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("requestDate")
    val requestDate: String?,
    @SerializedName("pets")
    val pets: List<PetApiModel>?,
    @SerializedName("dateFrom")
    val dateFrom: String?,
    @SerializedName("dateTo")
    val dateTo: String?,
    @SerializedName("status")
    val status: Int
) {
    data class PetApiModel(
        @SerializedName("id")
        val id: Long,
        @SerializedName("name")
        val name: String?,
        @SerializedName("breed")
        val breed: String?
    )
}
