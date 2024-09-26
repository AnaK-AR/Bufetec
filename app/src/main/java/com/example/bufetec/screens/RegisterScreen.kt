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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    // Recordar los valores ingresados
    val nombre = remember { mutableStateOf("") }
    val apellidoPaterno = remember { mutableStateOf("") }
    val apellidoMaterno = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }
    val confirmarContrasena = remember { mutableStateOf("") }

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
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Nombre(s)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Apellido Paterno
        TextField(
            value = apellidoPaterno.value,
            onValueChange = { apellidoPaterno.value = it },
            label = { Text("Apellido Paterno") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Apellido Materno
        TextField(
            value = apellidoMaterno.value,
            onValueChange = { apellidoMaterno.value = it },
            label = { Text("Apellido Materno") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Correo
        TextField(
            value = correo.value,
            onValueChange = { correo.value = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Contraseña
        TextField(
            value = contrasena.value,
            onValueChange = { contrasena.value = it },
            label = { Text("Crear una contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Confirmar Contraseña
        TextField(
            value = confirmarContrasena.value,
            onValueChange = { confirmarContrasena.value = it },
            label = { Text("Confirmar contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Registrar
        Button(
            onClick = {
                // Falta poner esto
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
