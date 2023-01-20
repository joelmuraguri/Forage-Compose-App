package com.example.forage_compose.ui.presentation.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.R
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.ui.presentation.views.destinations.InputScreenDestination
import com.example.forage_compose.ui.presentation.views.destinations.ListScreenDestination
import com.example.forage_compose.utils.DetailsScreenEvents
import com.example.forage_compose.viewmodels.DetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Destination
@Composable
fun DetailsScreen(
    forage: Forage,
    navigator: DestinationsNavigator,
    detailsViewModel: DetailsViewModel = hiltViewModel()
){
    
    val time = detailsViewModel.time
    val instantTime = Instant.ofEpochMilli(time)
    val zonedDateTime = instantTime.atZone(ZoneId.of("UTC"))
    var localTime = zonedDateTime.toLocalTime()

    var convertedTime = detailsViewModel.getLocalTime()

    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }

    val formatTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(convertedTime)
        }
    }

    val context = LocalContext.current

    val timeDialogState = rememberMaterialDialogState()

    var showMenu by remember { mutableStateOf(false) }

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
                    Text(text = forage.name)
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
                                detailsViewModel.onEvents(DetailsScreenEvents.OnDeleteForage(forage))
                                navigator.navigate(ListScreenDestination())
                            }) {

                                Text(text = "Delete Item")
                            }

                            DropdownMenuItem(onClick = {

                            }) {
                                Text(text = "Cancel Alarm")
                            }
                        }
                    }
                }
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
                    initialTime = convertedTime,
                    title = "Pick a time",
                    is24HourClock = true
                ) {
                    convertedTime = it
                }
            }

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
               navigator.navigate(InputScreenDestination())
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_edit_24), contentDescription = "edit_icon")
            }

        },
        content = {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {

                Text(
                    text = forage.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_location_on_24),
                            contentDescription = "location"
                        )
                        Spacer(modifier = Modifier.padding(8.dp) )
                        Text(text = forage.location)

                    }
                    Divider(modifier = Modifier.fillMaxWidth())
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                            contentDescription = "calendar"
                        )
                        Spacer(modifier = Modifier.padding(8.dp) )
                        if (forage.isSeason){
                            Text(text = "Currently in Season")
                        }
                        else{
                            Text(text = "Not currently in Season")
                        }
                    }
                    Divider(modifier = Modifier.fillMaxWidth())
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_notes_24),
                            contentDescription = "notes"
                        )
                        Spacer(modifier = Modifier.padding(8.dp) )
                        Text(text = forage.notes)

                    }
                    Divider(modifier = Modifier.fillMaxWidth())
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_alarm_24),
                            contentDescription = "notes"
                        )
                        Spacer(modifier = Modifier.padding(8.dp) )
                        Text(
                            text = formatTime,
                            color = Color.Red,
                        )
                    }
                    Divider(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    )
}
