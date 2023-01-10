package com.example.forage_compose.ui.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.R
import com.example.forage_compose.ui.presentation.components.Avatar
import com.example.forage_compose.ui.presentation.views.destinations.ListScreenDestination
import com.example.forage_compose.ui.presentation.views.destinations.LogInScreenDestination
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

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                                 IconButton(onClick = {
                                     navigator.popBackStack()
                                 }) {
                                     Icon(
                                         painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                                         contentDescription = "")
                                 }

                },
                title = {
                    Text(text = "User Profile")
                }
            )
        },

    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "Hi $name",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(12.dp)
            )
            Avatar()
            Text(
                text = "Welcome BACK",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(12.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                Text(
                    text = "NAME :",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(12.dp)
                )
                Text(
                    text = name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = "EMAIL :",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(12.dp)
                )
                Text(
                    text = email,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }


            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(32.dp)
            ) {
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
    }
}