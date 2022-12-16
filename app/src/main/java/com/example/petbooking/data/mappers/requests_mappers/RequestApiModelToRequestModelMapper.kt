package com.example.petbooking.data.mappers.requests_mappers

import com.example.petbooking.data.api_models.requests.RequestApiModel
import com.example.petbooking.data.databases.RequestsEntity
import com.example.petbooking.domain.models.requests.RequestModel
import com.example.petbooking.errors.BadDataResponseException

fun RequestApiModel.toRequestModel(): RequestModel =
    RequestModel(
        this.id,
        this.requestDate ?: throw BadDataResponseException(),
        this.pets?.map { it.toPetModel() } ?: emptyList(),
        this.dateFrom ?: "",
        this.dateTo ?: "",
        this.status
    )

fun RequestApiModel.PetApiModel.toPetModel(): RequestModel.PetModel =
    RequestModel.PetModel(
        this.id,
        this.name ?: "",
        this.breed ?: ""
    )

fun RequestApiModel.toRequestEntity(): RequestsEntity =
    RequestsEntity(
        this.id,
        this.requestDate ?: throw BadDataResponseException(),
        this.pets?.map { it.toPetModel() } ?: emptyList(),
        this.dateFrom ?: "",
        this.dateTo ?: "",
        this.status
    )

fun RequestsEntity.toRequestModel(): RequestModel =
    RequestModel(
        this.id,
        this.requestDate ,
        this.pets,
        this.dateFrom ,
        this.dateTo,
        this.status
    )