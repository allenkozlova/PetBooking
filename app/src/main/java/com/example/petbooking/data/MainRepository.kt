package com.example.petbooking.data

import android.content.Context
import com.example.petbooking.domain.SitterModel
import com.example.petbooking.domain.toSitterModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject


class MainRepository @Inject constructor (private val context: Context, private val apiService: ApiService) {
    var gson = Gson()

    suspend fun getUsers(): List<ApiUser> = apiService.getUsers()

    suspend fun getCats(): List<CatModel> = apiService.getCats()

    suspend fun getSitters(): List<SitterModel> = gson.fromJson<List<SitterApiModel>?>(loadJSONFromAsset(context), object : TypeToken<List<SitterApiModel>>() {}.type).map { it.toSitterModel() }


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
            return null
        }
        return json
    }
}