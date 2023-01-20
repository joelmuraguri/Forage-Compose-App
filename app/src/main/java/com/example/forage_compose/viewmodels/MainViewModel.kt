package com.example.forage_compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    repository : ForageRepo,
) : ViewModel() {

    private val _uiEvents = Channel<UiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    val forages = repository.getAll()

    fun onEvents(events: ListScreenEvents){
        when(events){
            is ListScreenEvents.DeleteAll -> {


            }
            else -> Unit
        }

    }

    private fun uiEvents(event: UiEvent){
        viewModelScope.launch {
            _uiEvents.send(event)

        }
    }
}

