package com.example.forage_compose.viewmodels

import com.example.forage_compose.domain.Forage

data class ViewState(
    val forage : List<Forage> = emptyList(),
    val isSeason : Boolean = false

)