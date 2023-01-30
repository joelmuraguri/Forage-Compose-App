package com.example.forage_compose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.domain.repo.ForageRepo
import com.example.forage_compose.utils.ListScreenEvents
import com.example.forage_compose.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : ForageRepo,
) : ViewModel() {

    var forage by mutableStateOf<Forage?>(null)
        private set


    private val _uiEvents = Channel<UiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    val forages = repo.getAll()

    private var deletedForage : Forage?= null


    fun onEvents(events: ListScreenEvents){
        when(events){
            is ListScreenEvents.DeleteAll -> {
                viewModelScope.launch {
                    repo.deleteAll()
                    uiEvents(
                        UiEvent.ShowSnackBar(
                            message = "All Forages Deleted",
                            action = "Undo"
                        )
                    )
                }
            }
            is  ListScreenEvents.OnUndoDeleteClick -> {
                viewModelScope.launch {
                    deletedForage?.let { forage ->
                        viewModelScope.launch {
                            repo.insert(forage)
                        }
                    }
                }
            }
        }
    }

    private fun uiEvents(event: UiEvent){
        viewModelScope.launch {
            _uiEvents.send(event)

        }
    }
}

