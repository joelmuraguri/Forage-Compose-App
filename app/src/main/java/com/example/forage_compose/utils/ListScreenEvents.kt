package com.example.forage_compose.utils

import com.example.forage_compose.domain.Forage

sealed class ListScreenEvents {

    data class OnForageClick(val forage: Forage) : ListScreenEvents()
    object OnAddForage : ListScreenEvents()


}