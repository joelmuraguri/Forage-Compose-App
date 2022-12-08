package com.example.forage_compose.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.R
import com.example.forage_compose.utils.ListScreenEvents
import com.example.forage_compose.utils.UiEvent
import com.example.forage_compose.viewmodels.MainViewModel

@Composable
fun ListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
){

    val forages = viewModel.forages.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }

        }
    }

    Scaffold(
        topBar = {
            ListTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvents(ListScreenEvents.OnAddForage)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "add_icon")
            }
        },
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ){
            items(forages.value){ forage ->
                ForageItem(
                    forage = forage,
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvents(ListScreenEvents.OnForageClick(forage))
                        }
                        .padding(16.dp),


                )
            }
        }
    }
}

@Composable
fun ListTopBar(){
    TopAppBar(

        title = {
            Text(text = "Forage")

        },
        actions = {
            IconButton(onClick = {  }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_more_vert_24), contentDescription = "more_vert_icon")
            }
        },
    )
}


