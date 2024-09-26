package com.example.navtemplate.screens
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.bufetec.R
import com.example.navtemplate.data.LoginUserRequest
import com.example.navtemplate.viewmodel.LoginUserState
import com.example.navtemplate.viewmodel.UserViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val loginState by userViewModel.login.collectAsState()
    LaunchedEffect(loginState) {
        when (val login = loginState) {
            is LoginUserState.Loading -> {}
            is LoginUserState.Success -> {
                snackbarHostState.showSnackbar("Login exitoso")
                navController.navigate("home")
                userViewModel.isUserLogged = true
            }
            is LoginUserState.Error -> {
                snackbarHostState.showSnackbar(login.errorMessage)
            }
            else -> {}
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color(0xFF0D47A1)
                        )
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart) // Ahora puedes usar align correctamente
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "Tec Logo",
                    modifier = Modifier
                        .size(230.dp),  // Ajusta el tamaño del logo del Tec
                    contentScale = ContentScale.Fit
                )
            }
            // Logo de Bufetec centrado y el texto debajo del logo
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp), // Aumentar espaciado para asegurar que el logo esté por encima
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "BufeTec",
                    modifier = Modifier
                        .size(380.dp)  // Tamaño más grande para el logo de Bufetec
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Fit
                )}
            Column {
                Spacer(modifier = Modifier.padding(vertical = 180.dp))
                Text(
                    "¡Bienvenido a BuffeTec!",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    ), modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            // Parte inferior con los campos de texto y botones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = userViewModel.email,
                    onValueChange = { userViewModel.email = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = userViewModel.password,
                    onValueChange = { userViewModel.password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(
                        onClick = {
                            userViewModel.loginUser(
                                LoginUserRequest(
                                    userViewModel.email,
                                    userViewModel.password
                                )
                            )
                        },
                        modifier = Modifier
                    ) {
                        Text("Ingresar")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 7.dp))
                    Button(
                        onClick = {
                            navController.navigate("home")
                        },
                        modifier = Modifier
                    ) {
                        Text("Continuar como invitado")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = {
                    navController.navigate("register")
                }) {
                    Text("¿Eres Nuevo? Regístrate Aquí", color = Color.White)
                }
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
                Row {
                    Button(
                        onClick = { },
                        modifier = Modifier
                    ) {
                        Text("Google")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    Button(
                        onClick = {  },
                        modifier = Modifier)
                    {
                        Text("Facebook")
                    }
                }
            }
            Loading(loginState)
        }
    }
}
@Composable
private fun Loading(loginState: LoginUserState) {
    when (loginState) {
        is LoginUserState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
        }
        else -> {}
    }
}