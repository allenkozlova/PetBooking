package com.example.petbooking.data

import com.google.gson.annotations.SerializedName

data class CatModel(
    @SerializedName("url")
    val url: String? = ""
)