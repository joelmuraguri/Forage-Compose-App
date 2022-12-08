package com.example.forage_compose.utils

import com.example.forage_compose.domain.Forage

sealed class InputScreenEvents{

    object OnSaveForage : InputScreenEvents()
    data class OnDeleteForage(val forage: Forage) : InputScreenEvents()
    data class OnNameChange(val name: String): InputScreenEvents()
    data class OnLocationChange(val location: String): InputScreenEvents()
    data class OnSeasonChange(val isSeason: Boolean): InputScreenEvents()
    data class OnNotesChange(val notes: String): InputScreenEvents()
    object OnUndoDeleteClick : InputScreenEvents()

}
