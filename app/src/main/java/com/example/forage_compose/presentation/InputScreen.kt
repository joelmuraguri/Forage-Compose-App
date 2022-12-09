package com.example.forage_compose.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.R
import com.example.forage_compose.presentation.destinations.ListScreenDestination
import com.example.forage_compose.utils.InputScreenEvents
import com.example.forage_compose.utils.UiEvent
import com.example.forage_compose.viewmodels.InputViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun InputScreen(
    navigator: DestinationsNavigator,
    viewModel: InputViewModel = hiltViewModel(),
){


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
            InputTopBar(navigator)
        },
        )
    {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = viewModel.name,
                onValueChange = {
                   viewModel.onEvent(InputScreenEvents.OnNameChange(it))
                },
                label = {
                    Text(text = "Name")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = viewModel.location,
                onValueChange = {
                   viewModel.onEvent(InputScreenEvents.OnLocationChange(it))
                },
                label = {
                    Text(text = "Location")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Checkbox(
                    checked = viewModel.season,
                    onCheckedChange = { isChecked ->
                        viewModel.onEvent(InputScreenEvents.OnSeasonChange(isChecked))
                    }
                )

                Text(text = "Currently in Season")
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = viewModel.notes,
                onValueChange = {
                    viewModel.onEvent(InputScreenEvents.OnNotesChange(it))
                },
                label = {
                    Text(text = "Notes")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        viewModel.onEvent(InputScreenEvents.OnSaveForage)
                        navigator.navigate(ListScreenDestination)
                    },
                ) {
                    Text(text = "SAVE")
                }


        }
    }
}


@Composable
fun InputTopBar(navigator: DestinationsNavigator){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navigator.popBackStack()
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "arrow_back_icon")
            }
        },
        title = {
                Text(text = "Input Screen")
        },
    )
}

