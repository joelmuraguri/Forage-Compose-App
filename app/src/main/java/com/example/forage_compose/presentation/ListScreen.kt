package com.example.forage_compose.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.R
import com.example.forage_compose.presentation.destinations.InputScreenDestination
import com.example.forage_compose.viewmodels.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun ListScreen(
    navigator: DestinationsNavigator,
    viewModel: MainViewModel = hiltViewModel(),
){

    val forages = viewModel.forages.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            ListTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.navigate(InputScreenDestination())
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
            items(forages.value){
                ForageItem(
                    forage = it,
                    navigator = navigator
                )
            }
        }
    }
}

@Composable
fun ListTopBar(){

    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current


    TopAppBar(

        title = {
            Text(text = "Forage")

        },
        actions = {
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.TopEnd)

            ) {
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(Icons.Default.MoreVert, "")
                }

                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    modifier = Modifier
                        .padding(12.dp)
                ) {

                    DropdownMenuItem(onClick = { Toast.makeText(context, "List Sorted", Toast.LENGTH_SHORT).show() }) {
                        Text(text = "Sort List")
                    }

                    DropdownMenuItem(onClick = { Toast.makeText(context, "All Forages Deleted", Toast.LENGTH_SHORT).show() }) {

                        Text(text = "Clear List")
                    }

                }
            }
        },
    )
}


