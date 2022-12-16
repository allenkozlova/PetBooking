package com.example.petbooking.data.repositories.requests

import android.content.Context
import com.example.petbooking.data.api_models.requests.RequestApiModel
import com.example.petbooking.data.api_models.sitters.SitterApiModel
import com.example.petbooking.data.datasources.RequestsDataSource
import com.example.petbooking.data.mappers.requests_mappers.toRequestEntity
import com.example.petbooking.data.mappers.requests_mappers.toRequestModel
import com.example.petbooking.data.mappers.sitters_mappers.toSitterModel
import com.example.petbooking.data.services.ApiService
import com.example.petbooking.domain.models.requests.RequestModel
import com.example.petbooking.domain.repositories.sitters.MainRepository
import com.example.petbooking.domain.models.sitters.SitterModel
import com.example.petbooking.domain.repositories.requests.RequestsRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

class RequestsRepositoryImpl @Inject constructor (private val context: Context, private val apiService: ApiService, private val requestsDataSource: RequestsDataSource):
    RequestsRepository {
    var gson = Gson()

    override suspend fun loadRequests() {
        val result = gson.fromJson<List<RequestApiModel>>(loadJSONFromAsset(context, "requests_list.json"), object : TypeToken<List<RequestApiModel>>() {}.type)
        requestsDataSource.updateRequests(result.map { it.toRequestEntity() })
    }

    override suspend fun getActiveRequests(): Flow<List<RequestModel>> = requestsDataSource.getActiveRequests().map { entities -> entities.map { it.toRequestModel() } }

    override suspend fun getCancelRequests(): Flow<List<RequestModel>> = requestsDataSource.getCancelRequests().map { entities -> entities.map { it.toRequestModel() } }

    private fun loadJSONFromAsset(context: Context, fileName: String): String? {
        var json: String? = null
        try {
            val from: InputStream = context.assets.open(fileName)
            val size: Int = from.available()
            val buffer = ByteArray(size)
            from.read(buffer)
            from.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }
}