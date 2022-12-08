package com.example.forage_compose.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.R
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.utils.DetailsScreenEvents
import com.example.forage_compose.utils.UiEvent
import com.example.forage_compose.viewmodels.DetailsViewModel


@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
){

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)

                else -> Unit
            }
        }

    }
    val forage by mutableStateOf<Forage?>(null)

    Scaffold(
        topBar = {
                 DetailsTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvents(DetailsScreenEvents.OnForageEdit(forage = forage!!))

            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_edit_24), contentDescription = "edit_icon")
            }

        },
        content = {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Details Screen")
            }
        }

    )

}

@Composable
fun DetailsTopBar(){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "arrow_back_icon")

            }
        },
        title = {
                Text(text = "Forage")

        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_more_vert_24), contentDescription = "more_vert_icon")
            }
        },
    )
}

@Composable
fun DetailsFAB(){

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

