package com.example.forage_compose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.domain.repo.ForageRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
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

//    private val _uiEvents = Channel<UiEvent>()
//    val uiEvent = _uiEvents.receiveAsFlow()


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

//    private var deletedForage : Forage?= null

//    fun onEvents(events: DetailsScreenEvents){
//        when(events){
//            is DetailsScreenEvents.OnDeleteForage -> {
//                viewModelScope.launch {
//                    deletedForage = events.forage
//                    repo.deleteForage(events.forage)
//                    sendUiEvents(UiEvent.ShowSnackBar(
//                        message = "${forage?.name } Deleted",
//                        action = "Undo"
//                    ))
//                }
//            }
//
//            is DetailsScreenEvents.OnUndoDeleteClick -> {
//                deletedForage?.let { forage ->
//                    viewModelScope.launch {
//                        repo.insert(forage)
//                    }
//                }
//            }
//        }
//    }
//
//    private fun sendUiEvents(event: UiEvent){
//        viewModelScope.launch {
//            _uiEvents.send(event)
//
//        }
//    }



}