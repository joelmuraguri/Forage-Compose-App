package com.example.forage_compose.utils

sealed class UiEvent{

    data class ShowSnackBar(
        val message : String, val action : String? = null
        ) : UiEvent()

}
