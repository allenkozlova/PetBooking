package com.example.petbooking.data.repositories

import android.content.Context
import com.example.petbooking.data.api_models.SitterApiModel
import com.example.petbooking.data.services.ApiService
import com.example.petbooking.domain.repositories.MainRepository
import com.example.petbooking.domain.models.SitterModel
import com.example.petbooking.data.mappers.toSitterModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor (private val context: Context, private val apiService: ApiService):
    MainRepository {
    var gson = Gson()

    override suspend fun getSitters(): List<SitterModel> = gson.fromJson<List<SitterApiModel>?>(loadJSONFromAsset(context), object : TypeToken<List<SitterApiModel>>() {}.type).map { it.toSitterModel() }

    private fun loadJSONFromAsset(context: Context): String? {
        var json: String? = null
        try {
            val from: InputStream = context.assets.open("sitters_list.json")
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