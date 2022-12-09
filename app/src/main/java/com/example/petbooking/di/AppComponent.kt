package com.example.petbooking.di

import android.content.Context
import com.example.petbooking.PetBookingApp
import dagger.android.AndroidInjector
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityModule::class, FragmentModule::class])
interface AppComponent: AndroidInjector<PetBookingApp> {

    override fun inject(instance: PetBookingApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): AppComponent
    }

}