package com.example.forage_compose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.domain.repo.ForageRepo
import com.example.forage_compose.utils.Constants
import com.example.forage_compose.utils.DetailsScreenEvents
import com.example.forage_compose.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repo: ForageRepo
) : ViewModel() {

    var forage by mutableStateOf<Forage?>(null)
        private set

    var name by mutableStateOf("")
        private set

    var location by mutableStateOf("")
        private set

    var season by mutableStateOf(false)
        private set

    var notes by mutableStateOf("")
        private set

    private val _uiEvents = Channel<UiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()


    init {
        val forageId = savedStateHandle.get<Int>("forageId")!!
        if (forageId != -1){
            viewModelScope.launch {
                repo.getForageById(forageId)?.let { forage ->
                    name = forage.name
                    location = forage.location
                    season = forage.isSeason
                    notes = forage.notes
                    this@DetailsViewModel.forage = forage
                }
            }
        }
    }

    fun onEvents(events: DetailsScreenEvents){
        when(events){
            is DetailsScreenEvents.OnForageEdit -> {
                sendUiEvents(UiEvent.Navigate(Constants.INPUT_SCREEN  + "?forageId=${events.forage.id}"))
            }
        }
    }

    private fun sendUiEvents(event: UiEvent){
        viewModelScope.launch {
            _uiEvents.send(event)

        }
    }



}