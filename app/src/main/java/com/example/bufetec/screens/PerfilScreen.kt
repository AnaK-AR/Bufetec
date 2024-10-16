package com.example.bufetec.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bufetec.R
import com.example.navtemplate.viewmodel.UserViewModel
@Composable
fun PerfilScreen(navController: NavController, appViewModel: UserViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        if (appViewModel.isUserLogged) {
            // Mostrar la vista de perfil completa para usuarios registrados
            Spacer(modifier = Modifier.height(65.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                IconButton(
                    onClick = { /* Acción para editar el perfil */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(30.dp)
                        .background(Color.White, shape = CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar perfil",
                        tint = Color.Blue
                    )
                }
            }
            Text(
                text = "Pedro Pascal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "#000001",  // Este seria el ID
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Column {
                ConfigOption(text = "Datos Personales", onClick = { /* Navegar */ })
                HorizontalDivider()
                ConfigOption(text = "Historial de Casos", onClick = { /* Navegar */ })
                HorizontalDivider()
                ConfigOption(text = "Accesibilidad", onClick = { /* Navegar */ })
                HorizontalDivider()
                ConfigOption(text = "Apariencia", onClick = { /* Navegar */ })
                HorizontalDivider()
                ConfigOption(text = "Privacidad y Seguridad", onClick = { /* Navegar */ })

                Button(
                    onClick = {
                        appViewModel.logoutUser()
                        navController.navigate("login") {
                            popUpTo("perfil") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(text = "Cerrar Sesión")
                }
            }
        } else {
            // Mostrar mensaje para usuarios no registrados
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Esta sección es solo para usuarios registrados.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.navigate("login") {
                        popUpTo("perfil") { inclusive = true }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Iniciar Sesión")
            }
        }
    }
}


@Composable
fun ConfigOption(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
