package com.example.forage_compose.utils

import com.example.forage_compose.domain.Forage
import java.time.LocalTime

sealed class DetailsScreenEvents{

    object OnUndoDeleteClick : DetailsScreenEvents()
    data class OnDeleteForage(val forage: Forage) : DetailsScreenEvents()
    data class OnTimeChange(val time: Long): DetailsScreenEvents()

}
