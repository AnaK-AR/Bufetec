package com.example.navtemplate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bufetec.R
import com.example.navtemplate.viewmodel.UserViewModel

@Composable
fun AbogadosInfoScreen(navController: NavController, appViewModel: UserViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(21.dp)  // Espaciado entre los rectángulos
    ) {
        Spacer(modifier = Modifier.height(65.dp))

        // Rectángulos de abogados con funcionalidad de expansión y color azul
        AbogadoCard(
            imageId = R.drawable.agramont,
            name = "Mtra.Vibiana Agramont",
            specialty = "Directora de Bufetec / Especialista Civil y Mercantil",
            email = "vagramont@tec.mx,",
            phone = "+52 88 1234 5678"
        )
        AbogadoCard(
            imageId = R.drawable.manolo,
            name = "Mtro. Manolo Martínez",
            specialty = "Especialista Familiar ",
            email = "manolomtz@tec.mx",
            phone = "+52 88 8765 4321"
        )
        AbogadoCard(
            imageId = R.drawable.vero,
            name = "Lic. Verónica González ",
            specialty = "Especialista Familiar ",
            email = "veronica.gr@tec.mx",
            phone = "+52 88 1122 3344"
        )
    }
}

@Composable
fun AbogadoCard(imageId: Int, name: String, specialty: String, email: String, phone: String) {
    var isClicked by remember { mutableStateOf(false) }  // Variable para controlar si está expandido

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF0067C0),  // Cambiar el color de fondo a azul
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { isClicked = !isClicked }  // Alternar estado de expansión al hacer clic
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Imagen de perfil a la izquierda
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Imagen de perfil",
                modifier = Modifier
                    .size(80.dp)  // Tamaño de la imagen
                    .padding(end = 24.dp)  // Espaciado entre la imagen y el texto
            )

            // Columna de información del abogado
            Column {
                Text(
                    text = name,
                    fontSize = 20.sp,  // Tamaño de la fuente del nombre
                    fontWeight = FontWeight.Bold,
                    color = Color.White  // Texto en blanco
                )
                Text(
                    text = specialty,
                    fontSize = 18.sp,  // Tamaño de la fuente de la especialidad
                    color = Color.White  // Texto en blanco
                )
            }
        }

        // Información adicional mostrada solo si está expandido
        if (isClicked) {
            Spacer(modifier = Modifier.height(8.dp))  // Espaciado entre la información básica y la adicional
            Column(
                modifier = Modifier.padding(start = 75.dp)  // Alineación del texto adicional con el texto principal
            ) {
                Text(
                    text = "Correo: $email",
                    fontSize = 16.sp,
                    color = Color.White  // Texto en blanco para la información adicional
                )
                Text(
                    text = "Teléfono: $phone",
                    fontSize = 16.sp,
                    color = Color.White  // Texto en blanco para la información adicional
                )
            }
        }
    }
}
