package com.example.forage_compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.forage_compose.domain.Alarm
import com.example.forage_compose.utils.alarm.AlarmRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val repo: AlarmRepo
) : ViewModel() {

    fun saveNewAlarm(time : Long, requestCode : Int){
        viewModelScope.launch {
            repo.insert(
                Alarm(alarmId = requestCode, time = time )
            )
        }
    }

    fun scheduleAlarm(){
        viewModelScope.launch {

        }

    }

    fun  cancelAlarm(){
        viewModelScope.launch {

        }
    }
}