package com.example.navtemplate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navtemplate.data.LoginUserRequest
import com.example.navtemplate.data.RegisterUserRequest
import com.example.navtemplate.viewmodel.LoginUserState
import com.example.navtemplate.viewmodel.RegisterUserState
import com.example.navtemplate.viewmodel.UserViewModel

@Composable
fun RegisterScreen(navController: NavController,userViewModel: UserViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val userState by userViewModel.login.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                navController.navigate("login")
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Crear Cuenta",
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Crea una cuenta para comenzar.",
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre(s)
        TextField(
            value = userViewModel.user_firstname,
            onValueChange = { userViewModel.user_firstname = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Apellido Paterno
        TextField(
            value = userViewModel.user_lastname,
            onValueChange = { userViewModel.user_lastname = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Correo
        TextField(
            value = userViewModel.user_email,
            onValueChange = { userViewModel.user_email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Username
        TextField(
            value = userViewModel.user_username,
            onValueChange = { userViewModel.user_username = it },
            label = { Text("Username ") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Contraseña
        TextField(
            value = userViewModel.password,
            onValueChange = { userViewModel.password = it },
            label = { Text("Password ") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        // Botón de Registrar
        Button(
            onClick = {
                userViewModel.registerUser(
                    RegisterUserRequest(
                        userViewModel.user_email,
                        userViewModel.password,
                        userViewModel.user_firstname,
                        userViewModel.user_lastname,
                        userViewModel.user_username,
                    )
                )
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
