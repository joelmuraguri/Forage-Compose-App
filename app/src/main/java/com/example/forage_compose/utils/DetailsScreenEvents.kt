package com.example.forage_compose.utils

import com.example.forage_compose.domain.Forage

sealed class DetailsScreenEvents{

    data class OnForageEdit(val forage: Forage): DetailsScreenEvents()

}
