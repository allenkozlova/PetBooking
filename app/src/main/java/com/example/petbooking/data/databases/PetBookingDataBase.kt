package com.example.petbooking.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RequestsEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class PetBookingDataBase: RoomDatabase() {

    abstract fun requestsDao(): RequestsDao
}