package com.example.petbooking.data.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.petbooking.domain.models.requests.RequestModel
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken

@Entity(tableName = "REQUESTS_TABLE")
data class RequestsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "request_id")
    var id: Long = -1,

    @ColumnInfo(name = "request_date")
    var requestDate : String = "",

    @ColumnInfo(name = "pets")
    var pets : List<RequestModel.PetModel> = emptyList(),

    @ColumnInfo(name = "date_from")
    var dateFrom : String = "",

    @ColumnInfo(name = "date_to")
    var dateTo : String = "",

    @ColumnInfo(name = "status")
    var status : Int = -1
)

object Converters {
    @TypeConverter
    fun fromString(value: String?): List<RequestModel.PetModel> {
        val listType = object : TypeToken<List<RequestModel.PetModel>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<RequestModel.PetModel?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}