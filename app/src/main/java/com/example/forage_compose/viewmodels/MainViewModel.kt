package com.example.forage_compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forage_compose.domain.repo.ForageRepo
import com.example.forage_compose.utils.Constants
import com.example.forage_compose.utils.ListScreenEvents
import com.example.forage_compose.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : ForageRepo,
) : ViewModel() {

    val forages = repository.getAll()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvents(events: ListScreenEvents){
        when(events){
            is ListScreenEvents.OnAddForage -> {
                sendUIEvents(UiEvent.Navigate(Constants.INPUT_SCREEN))
            }
            is ListScreenEvents.OnForageClick -> {
                sendUIEvents(UiEvent.Navigate(Constants.DETAILS_SCREEN + "?forageId=${events.forage.id}"))
            }

        }

    }

    private fun sendUIEvents(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}

