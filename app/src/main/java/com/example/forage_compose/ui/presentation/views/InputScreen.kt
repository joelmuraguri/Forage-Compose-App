package com.example.forage_compose.ui.presentation.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.R
import com.example.forage_compose.ui.presentation.views.destinations.ListScreenDestination
import com.example.forage_compose.utils.InputScreenEvents
import com.example.forage_compose.utils.UiEvent
import com.example.forage_compose.viewmodels.InputViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.Instant
import java.time.ZoneId


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

    val timeDialogState = rememberMaterialDialogState()

    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val time = viewModel.time
    val instantTime = Instant.ofEpochMilli(time)
    val zonedDateTime = instantTime.atZone(ZoneId.of("UTC"))
    var localTime = zonedDateTime.toLocalTime()

////    var convertedTime = viewModel.getTime()
//
//    val pickedTime = viewModel.time
//
//
//    val formatTime by remember {
//        derivedStateOf {
//            DateTimeFormatter
//                .ofPattern("hh:mm")
//                .format(localTime)
//        }
//    }

    Scaffold(
        topBar = {
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
                                timeDialogState.show()
                            }) {
                                Text(text = "Set Reminder")
                            }

                            DropdownMenuItem(onClick = {

                            }) {
                                Text(text = "Cancel Alarm")
                            }
                        }
                    }

                },

            )

            MaterialDialog(
                dialogState = timeDialogState,
                buttons = {
                    positiveButton(text = "Ok") {
                        Toast.makeText(
                            context,
                            "Clicked ok",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    negativeButton(text = "Cancel")
                }
            ) {
                timepicker(
                    initialTime = localTime,
                    title = "Pick a time",
                    is24HourClock = true
                ) {
                    localTime = it
                }
            }
        },
        bottomBar = {
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(60.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_discard_24),
                                contentDescription = "discard",
                                modifier = Modifier
                                    .size(37.dp)

                            )
                            Text(
                                text = "Discard",
                                fontSize = 18.sp
                            )
                        }

                    }
                    Button(
                        onClick = {
                            viewModel.onEvent(InputScreenEvents.OnSaveForage)
                            navigator.navigate(ListScreenDestination)
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(60.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_save_24),
                                contentDescription = "save",
                                modifier = Modifier
                                    .size(37.dp)
                            )
                            Text(
                                text = "Save",
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
        )
    {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
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

            OutlinedTextField(
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

            OutlinedTextField(
                value = viewModel.notes,
                onValueChange = {
                    viewModel.onEvent(InputScreenEvents.OnNotesChange(it))
                },
                label = {
                    Text(text = "Notes")
                },
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

