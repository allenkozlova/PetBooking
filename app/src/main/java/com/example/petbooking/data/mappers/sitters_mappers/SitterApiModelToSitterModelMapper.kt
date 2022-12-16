package com.example.petbooking.data.mappers.sitters_mappers

import com.example.petbooking.data.api_models.sitters.SitterApiModel
import com.example.petbooking.domain.models.sitters.SitterModel
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