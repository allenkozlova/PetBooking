package com.example.petbooking.data

import com.google.gson.annotations.SerializedName

data class SitterApiModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("sitterPhoto")
    val sitterPhoto: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("district")
    val district: String?,
    @SerializedName("mark")
    val mark: Int,
    @SerializedName("feedbacks")
    val feedbacks: Int,
    @SerializedName("price")
    val price: Double
)