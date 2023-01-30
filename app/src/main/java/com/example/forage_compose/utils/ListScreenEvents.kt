package com.example.forage_compose.utils

sealed class ListScreenEvents {

    object DeleteAll : ListScreenEvents()
    object OnUndoDeleteClick : ListScreenEvents()

}