package com.example.petbooking.data.mappers

import com.example.petbooking.data.api_models.SitterApiModel
import com.example.petbooking.domain.models.SitterModel
import com.example.petbooking.errors.BadDataResponseException

fun SitterApiModel.toSitterModel(): SitterModel =
    SitterModel(
        this.id,
        this.sitterPhoto ?: "",
        this.name ?: throw BadDataResponseException(),
        this.city ?: throw BadDataResponseException(),
        this.district ?: "",
        this.mark,
        this.feedbacks,
        this.price
    )