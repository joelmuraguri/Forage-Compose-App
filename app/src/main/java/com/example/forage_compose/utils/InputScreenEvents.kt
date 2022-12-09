package com.example.forage_compose.utils

sealed class InputScreenEvents{

    object OnSaveForage : InputScreenEvents()
    data class OnNameChange(val name: String): InputScreenEvents()
    data class OnLocationChange(val location: String): InputScreenEvents()
    data class OnSeasonChange(val isSeason: Boolean): InputScreenEvents()
    data class OnNotesChange(val notes: String): InputScreenEvents()

}
