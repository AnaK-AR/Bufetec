package com.example.navtemplate.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Parte azul (Superior)
            Box(
                modifier = Modifier
                    .weight(0.48f)  // Mantener el espacio azul más pequeño para dar más espacio al contenido blanco
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF0A3780), Color(0xFF0067C0))
                        )
                    )
            ) {
                // Logo del Tec a la izquierda
                Image(
                    painter = painterResource(id = R.drawable.logotec),
                    contentDescription = "Tec Logo",
                    modifier = Modifier
                        .size(200.dp)  // Aumentar el tamaño
                        .align(Alignment.TopStart)
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                )

                // Logo de Bufetec centrado
                Image(
                    painter = painterResource(id = R.drawable.bufetec),
                    contentDescription = "BufeTec",
                    modifier = Modifier
                        .size(380.dp)  // Aumentar el tamaño
                        .align(Alignment.BottomCenter)
                        .padding(top = 170.dp)  // Aumentar espaciado para evitar que toquen
                )
            }

            // Parte blanca (Inferior)
            Box(
                modifier = Modifier
                    .weight(0.5f)  // Más espacio para el contenido blanco
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // TextField para correo
                    TextField(
                        value = userViewModel.user_email,
                        onValueChange = { userViewModel.user_email = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    )

                    // TextField para contraseña
                    TextField(
                        value = userViewModel.password,
                        onValueChange = { userViewModel.password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botones de Ingresar e Invitado
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                userViewModel.loginUser(
                                    LoginUserRequest(
                                        userViewModel.user_email,
                                        userViewModel.password
                                    )
                                )

                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF0D47A1))
                        ) {
                            Text("Ingresar", color = Color.White)
                        }

                        Button(
                            onClick = {
                                navController.navigate("home")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF0D47A1))
                        ) {
                            Text("Invitado", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Botón de registro
                    TextButton(onClick = {
                        navController.navigate("register")
                    }) {
                        Text("¿Eres Nuevo? Regístrate Aquí", color = Color(0xFF0D47A1))
                    }

                    // Divider
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.Gray,
                        thickness = 1.dp
                    )

                    // Botones de Google y Facebook con imágenes PNG, forma de círculo
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Login con Google",
                            modifier = Modifier
                                .size(60.dp)  // Aumentar el tamaño
                                .clip(CircleShape)
                                .clickable { /* Acción para login con Google */ }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "Login con Facebook",
                            modifier = Modifier
                                .size(60.dp)  // Aumentar el tamaño
                                .clip(CircleShape)
                                .clickable { /* Acción para login con Facebook */ }
                        )
                    }
                }
            }
        }

        Loading(loginState)
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
