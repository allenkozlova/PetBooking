package com.example.petbooking.di.modules

import androidx.annotation.NonNull
import com.example.petbooking.BuildConfig
import com.example.petbooking.data.repositories.MainRepositoryImpl
import com.example.petbooking.data.services.ApiService
import com.example.petbooking.domain.repositories.MainRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.FIRST_HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun getApi(@NonNull retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository = mainRepositoryImpl

}