package com.example.petbooking.di

import com.example.petbooking.presentation.ui.BaseActivity
import com.example.petbooking.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}