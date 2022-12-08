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
import com.example.forage_compose.utils.InputScreenEvents
import com.example.forage_compose.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: ForageRepo
): ViewModel() {


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

    private var deletedForage : Forage?= null

    init {
        val forageId = savedStateHandle.get<Int>("forageId")!!
        if (forageId != -1){
            viewModelScope.launch {
                repo.getForageById(forageId)?.let { forage ->
                    name = forage.name
                    location = forage.location
                    season = forage.isSeason
                    notes = forage.notes
                    this@InputViewModel.forage = forage
                }
            }
        }
    }


    fun onEvent(events: InputScreenEvents){
        when(events){
            is InputScreenEvents.OnNameChange -> {
                name = events.name
            }
            is InputScreenEvents.OnLocationChange -> {
                location = events.location
            }
            is InputScreenEvents.OnSeasonChange -> {
                season = events.isSeason
            }
            is InputScreenEvents.OnNotesChange -> {
                notes = events.notes
            }
            is InputScreenEvents.OnSaveForage -> {
                viewModelScope.launch {
                    if (name.isBlank() && location.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "name and location can't be empty"
                        ))
                        return@launch
                    }
                    repo.insert(
                        Forage(
                            id = forage?.id,
                            name = name,
                            location = location,
                            isSeason = forage?.isSeason ?: false,
                            notes = forage?.notes ?: ""
                        )
                    )
                    sendUiEvent(UiEvent.Navigate(Constants.LIST_SCREEN))
                }
            }
            is InputScreenEvents.OnDeleteForage -> {
                viewModelScope.launch {
                    deletedForage = events.forage
                    repo.deleteForage(events.forage)
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "${forage?.name } Deleted",
                        action = "Undo"
                    ))
                }
            }

            is InputScreenEvents.OnUndoDeleteClick -> {
                deletedForage?.let { forage ->
                    viewModelScope.launch {
                        repo.insert(forage)
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }
}