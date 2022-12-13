package com.example.petbooking.presentation.ui.main_screen.view_states

interface BaseViewState {

    val itemType: Int

    companion object{
        const val SITTER = 0
        const val MAP = 1
    }
}