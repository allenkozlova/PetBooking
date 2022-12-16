package com.example.petbooking.data.repositories.sitters

import android.content.Context
import com.example.petbooking.data.api_models.requests.RequestApiModel
import com.example.petbooking.data.api_models.sitters.SitterApiModel
import com.example.petbooking.data.mappers.requests_mappers.toRequestModel
import com.example.petbooking.data.mappers.sitters_mappers.toSitterModel
import com.example.petbooking.data.services.ApiService
import com.example.petbooking.domain.models.requests.RequestModel
import com.example.petbooking.domain.repositories.sitters.MainRepository
import com.example.petbooking.domain.models.sitters.SitterModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor (private val context: Context, private val apiService: ApiService):
    MainRepository {
    var gson = Gson()

    override suspend fun getSitters(): List<SitterModel> = gson.fromJson<List<SitterApiModel>?>(loadJSONFromAsset(context, "sitters_list.json"), object : TypeToken<List<SitterApiModel>>() {}.type).map { it.toSitterModel() }

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