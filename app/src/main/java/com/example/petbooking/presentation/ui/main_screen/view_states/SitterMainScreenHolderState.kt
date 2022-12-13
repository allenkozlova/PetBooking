package com.example.petbooking.presentation.ui.main_screen.view_states

import com.example.petbooking.domain.models.SitterModel

class SitterMainScreenHolderState(val sitter: SitterModel): BaseViewState {
    override val itemType = BaseViewState.SITTER
}