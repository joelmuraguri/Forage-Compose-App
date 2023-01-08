package com.example.forage_compose.presentation.views

import android.app.TimePickerDialog
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
import com.example.forage_compose.presentation.destinations.InputScreenDestination
import com.example.forage_compose.presentation.destinations.ListScreenDestination
import com.example.forage_compose.utils.DetailsScreenEvents
import com.example.forage_compose.viewmodels.DetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.*

@Destination
@Composable
fun DetailsScreen(
    forage: Forage,
    navigator: DestinationsNavigator,
){

    Scaffold(
        topBar = {
                 DetailsTopBar(navigator,forage)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
               navigator.navigate(InputScreenDestination())
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_edit_24), contentDescription = "edit_icon")
            }

        },
        content = {
            DetailsContent(forage = forage )
        }

    )

}

@Composable
fun DetailsTopBar(navigator: DestinationsNavigator, forage: Forage, detailsViewModel: DetailsViewModel = hiltViewModel()){


    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val state = detailsViewModel.state


    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val timePicker = TimePickerDialog(
        context,
        {_, mHour : Int, mMinute: Int ->
            state.isTimeChanged = "$mHour:$mMinute"
        }, hour, minute, false
    )

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
                        timePicker.show()
                    }) {
                        Text(text = "Set Reminder")
                    }

                    DropdownMenuItem(onClick = {
//                        Toast.makeText(context, "${forage.name} Deleted", Toast.LENGTH_SHORT).show()
                        detailsViewModel.onEvents(DetailsScreenEvents.OnDeleteForage(forage))
                        navigator.navigate(ListScreenDestination())
                    }) {

                        Text(text = "Delete Item")
                    }

                }
            }
        }
    )
}

@Composable
fun DetailsContent(
    forage: Forage,
    viewModel: DetailsViewModel = hiltViewModel()
){


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
                    contentDescription = "calender"
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
                    painter = painterResource(id = R.drawable.ic_baseline_time_24),
                    contentDescription = "notes"
                )
                Spacer(modifier = Modifier.padding(8.dp) )
                Text(
                    text = viewModel.state.isTimeChanged,
                    color = Color.Red,
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}


private fun createWorkRequest(){

}
