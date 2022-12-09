package com.example.petbooking.domain

import com.example.petbooking.data.SitterApiModel

fun SitterApiModel.toSitterModel(): SitterModel =
    SitterModel(
        this.id,
        this.sitterPhoto ?: "",
        this.name ?: "",
        this.city ?: "",
        this.district ?: "",
        this.mark,
        this.feedbacks,
        this.price
    )