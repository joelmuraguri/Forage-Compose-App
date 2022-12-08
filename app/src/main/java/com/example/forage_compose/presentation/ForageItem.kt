package com.example.forage_compose.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forage_compose.domain.Forage

@Composable
fun ForageItem(
    forage: Forage,
    modifier: Modifier = Modifier,



){
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .clickable {

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

