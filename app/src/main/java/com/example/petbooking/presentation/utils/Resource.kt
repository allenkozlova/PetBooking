package com.example.petbooking.presentation.utils

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Success<T>(val value: T) : Resource<T>()
    class Error<T>(val message: String?) : Resource<T>()
}