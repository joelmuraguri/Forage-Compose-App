package com.example.forage_compose.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.presentation.destinations.ListScreenDestination
import com.example.forage_compose.presentation.destinations.LogInScreenDestination
import com.example.forage_compose.viewmodels.AuthViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun ProfileScreen(
    viewModel: AuthViewModel = hiltViewModel(), navigator: DestinationsNavigator
){
    viewModel.currentUser?.let {
        UserInfo(viewModel = viewModel, navigator = navigator, name = it.displayName.toString(), email = it.email.toString())
    }

}

@Composable
fun UserInfo(viewModel: AuthViewModel , navigator: DestinationsNavigator, name: String, email: String) {

   Column(
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {

       Text(text = "Hi $name")
       Text(text = "Welcome BACK")


       Row(
            verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center,
           modifier = Modifier
               .fillMaxWidth()
               .padding(12.dp)
       ) {
           Text(text = "Name")
           Text(text = name)

       }

       Row(
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center,
           modifier = Modifier
               .fillMaxWidth()
               .padding(12.dp)
       ) {
           Text(text = "Email :")
           Text(text = email)

       }


       Button(onClick = {
           viewModel.logout()
           navigator.navigate(LogInScreenDestination){
               popUpTo(ListScreenDestination){
                   inclusive = true
               }
           }
       },
           shape = RoundedCornerShape(20.dp),
           modifier = Modifier
               .fillMaxWidth(2f)
       ) {
          Text(text = "Logout")
       }
   }

}