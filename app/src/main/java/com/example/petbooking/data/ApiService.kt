package com.example.petbooking.data

import com.example.petbooking.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<ApiUser>

    @GET("more-users")
    suspend fun getMoreUsers(): List<ApiUser>

    @GET("error")
    suspend fun getUsersWithError(): List<ApiUser>

    @GET(BuildConfig.SECOND_HOST + "/v1/images/search?order=DESC&api_key=7fd8c10a-a96d-4e0f-8d6a-6a83ca4ffbce")
    suspend fun getCats(@Query("page") perPage: Int = 25, @Query("limit") limit: Int = 20): List<CatModel>

}