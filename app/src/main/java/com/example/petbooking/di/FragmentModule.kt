package com.example.petbooking.di

import com.example.petbooking.presentation.ui.MainScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainScreenFragment(): MainScreenFragment
}