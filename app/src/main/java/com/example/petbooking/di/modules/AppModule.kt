package com.example.petbooking.di.modules

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.example.petbooking.BuildConfig
import com.example.petbooking.data.databases.PetBookingDataBase
import com.example.petbooking.data.repositories.sitters.MainRepositoryImpl
import com.example.petbooking.data.services.ApiService
import com.example.petbooking.data.datasources.RequestsDataSource
import com.example.petbooking.data.repositories.requests.RequestsRepositoryImpl
import com.example.petbooking.domain.repositories.requests.RequestsRepository
import com.example.petbooking.domain.repositories.sitters.MainRepository
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

    @Provides
    @Singleton
    fun provideDataBase(context: Context): PetBookingDataBase {
        return Room.databaseBuilder(
            context,
            PetBookingDataBase::class.java,
            "PetBookingDataBase"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideRequestsDataSource(db: PetBookingDataBase): RequestsDataSource = RequestsDataSource(db)

    @Provides
    fun provideRequestsRepository(
        requestsRepositoryImpl: RequestsRepositoryImpl
    ): RequestsRepository = requestsRepositoryImpl

}