package com.example.forage_compose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.forage_compose.R
import com.example.forage_compose.ui.theme.ForageComposeTheme

@Composable
fun AppLogo(){

    Box(
        modifier = Modifier
            .fillMaxWidth(),

        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                2.dp
            )
        ) {

            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "app_logo",
                modifier = Modifier
                    .size(300.dp)
            )

            Text(
                text = "Forage App",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }



}

@Preview(showBackground = true)
@Composable
fun AppLogoPreview(){
    ForageComposeTheme {
        AppLogo()
    }
}