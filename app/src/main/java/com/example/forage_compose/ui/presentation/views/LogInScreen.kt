package com.example.forage_compose.ui.presentation.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.forage_compose.domain.auth.Resource
import com.example.forage_compose.ui.presentation.components.AppLogo
import com.example.forage_compose.ui.presentation.views.destinations.ListScreenDestination
import com.example.forage_compose.ui.presentation.views.destinations.SignUpScreenDestination
import com.example.forage_compose.viewmodels.AuthViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo

@Destination(start = true)
@Composable
fun LogInScreen(
    navigator: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel()
){

    Surface(
        modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            AppLogo()
            LoginInputFields(viewModel, navigator)

        }

    }




}

@Composable
fun LoginInputFields(
    viewModel: AuthViewModel,
    navigator: DestinationsNavigator
){

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authResource = viewModel.loginFlow.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        )

        Box(
            modifier = Modifier
                .padding(22.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(22.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.login(email, password)
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(2f)
                ) {
                    Text(text = "Login")
                }

                TextButton(
                    onClick = {
                        navigator.navigate(SignUpScreenDestination)
                    },
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Don't have an account?")
                        Text(text = "Click here to sign up")
                    }
                }

            }

            authResource.value?.let {
                when(it){
                    is Resource.Failure -> {
                        val context = LocalContext.current
                        Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .scale(0.5f)
                        )
                    }
                    is Resource.Success -> {
                        LaunchedEffect(Unit){
                            navigator.navigate(ListScreenDestination){
                                popUpTo(ListScreenDestination) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun InputFieldsPreview(){
//    ForageComposeTheme {
//        LogInScreen()
//    }
//}