package com.example.petbooking.di.modules

import com.example.petbooking.presentation.ui.favorites.FavoritesFragment
import com.example.petbooking.presentation.ui.main_screen.MainScreenFragment
import com.example.petbooking.presentation.ui.profile.ProfileFragment
import com.example.petbooking.presentation.ui.requests.RequestsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainScreenFragment(): MainScreenFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    abstract fun contributeRequestsFragment(): RequestsFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment
}