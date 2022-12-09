package com.example.forage_compose.utils

import com.example.forage_compose.domain.Forage

sealed class DetailsScreenEvents{

    object OnUndoDeleteClick : DetailsScreenEvents()
    data class OnDeleteForage(val forage: Forage) : DetailsScreenEvents()


}
