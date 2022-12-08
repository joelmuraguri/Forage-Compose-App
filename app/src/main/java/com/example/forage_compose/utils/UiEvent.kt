package com.example.forage_compose.utils

sealed class UiEvent{
    object PopBackStack : UiEvent()
    data class ShowSnackBar(
        val message : String, val action : String? = null
        ) : UiEvent()
    data class Navigate( val route : String) : UiEvent()
}
