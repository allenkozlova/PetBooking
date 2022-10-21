package com.example.petbooking

import android.app.Application
import android.content.Context
import com.example.petbooking.di.AppComponent
import com.example.petbooking.di.AppModule
import com.example.petbooking.di.DaggerAppComponent

class PetBookingApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }
}

val Context.appComponent: AppComponent
get() = when(this) {
    is PetBookingApp -> appComponent
    else -> this.applicationContext.appComponent
}