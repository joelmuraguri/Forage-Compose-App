package com.example.forage_compose.ui.presentation.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.forage_compose.ui.presentation.components.ForageItem
import com.example.forage_compose.ui.presentation.views.destinations.InputScreenDestination
import com.example.forage_compose.ui.presentation.views.destinations.ProfileScreenDestination
import com.example.forage_compose.utils.ListScreenEvents
import com.example.forage_compose.utils.UiEvent
import com.example.forage_compose.viewmodels.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ListScreen(
    navigator: DestinationsNavigator,
    viewModel: MainViewModel = hiltViewModel(),
){

    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val forages = viewModel.forages.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
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

                            DropdownMenuItem(onClick = {
                                navigator.navigate(ProfileScreenDestination)
                            }) {

                                Text(text = "Profile Screen")
                            }

                            DropdownMenuItem(onClick = { Toast.makeText(context, "List Sorted", Toast.LENGTH_SHORT).show() }) {
                                Text(text = "Sort List")
                            }

                            DropdownMenuItem(onClick = {
                                viewModel.onEvents(ListScreenEvents.DeleteAll)
                            }) {
                                Text(text = "Clear List")
                            }
                        }
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                navigator.navigate(InputScreenDestination())
            },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .width(150.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription = "add_icon")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "New Forage")
                }
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



