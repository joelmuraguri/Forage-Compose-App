package com.example.forage_compose.ui.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forage_compose.domain.Forage
import com.example.forage_compose.ui.presentation.views.destinations.DetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ForageItem(
    forage: Forage,
    navigator: DestinationsNavigator
){
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            onClick = {
                navigator.navigate(DetailsScreenDestination(forage))
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),

            ) {
                Text(
                    text = forage.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = forage.location,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }

}

