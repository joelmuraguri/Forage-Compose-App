package com.example.forage_compose.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forage_compose.R
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.presentation.destinations.InputScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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
            DetailsContent(forage = forage)
        }

    )

}

@Composable
fun DetailsTopBar(navigator: DestinationsNavigator, forage: Forage){


    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current


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

                    DropdownMenuItem(onClick = { Toast.makeText(context, "Reminder set", Toast.LENGTH_SHORT).show() }) {
                        Text(text = "Set Reminder")
                    }

                    DropdownMenuItem(onClick = { Toast.makeText(context, "${forage.name} Item deleted", Toast.LENGTH_SHORT).show() }) {

                        Text(text = "Delete Item")
                    }

                }
            }
        }
    )
}

@Composable
fun DetailsContent(forage: Forage){
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
    }
}

