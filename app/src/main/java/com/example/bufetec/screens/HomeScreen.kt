package com.example.navtemplate.screens

import NavigationDrawer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bufetec.R
import com.example.navtemplate.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, appViewModel: UserViewModel) {
    // Drawer and Scaffold states
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawer(navController, appViewModel) { destination ->
                    scope.launch { drawerState.close() }
                    navController.navigate(destination)
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Home Screen") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            content = { padding ->
                // Main content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0xFF003366), Color(0xFF66A3FF))
                            )
                        )
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Contenedor fijo con Box para el logo
                    Box(
                        modifier = Modifier
                            .height(200.dp)  // Mantener contenedor fijo
                            .fillMaxWidth(),  // Rellenar a lo ancho para centrar mejor el contenido
                        contentAlignment = Alignment.Center
                    ) {
                        // Logo ajustado con fillMaxHeight para escalar en el contenedor
                        Image(
                            painter = painterResource(id = R.drawable.bufetec),
                            contentDescription = "BufeTec Logo",
                            modifier = Modifier
                                .height(200.dp)  // Ajustar tamaño interno sin afectar el contenedor
                                .width(300.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    // Texto de bienvenida en blanco
                    Text(
                        "¡Bienvenidos a BufeTec!",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botones sin contenedor blanco
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            HomeButton(navController, "Biblioteca", Icons.Default.Book, "biblioteca")
                            HomeButton(navController, "Recursos Educativos", Icons.Default.School, "recursos educativos")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            HomeButton(navController, "Info de Abogados", Icons.Default.People, "abogados")
                            HomeButton(navController, "Citas", Icons.Default.CalendarToday, "citas")
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun HomeButton(navController: NavController, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, route: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(140.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                navController.navigate(route)  // Navegar a la pantalla correspondiente
            }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "$label Icon",
            modifier = Modifier
                .size(48.dp)
                .padding(bottom = 8.dp),
            tint = Color(0xFF0052CC)  // Cambiar el color del icono a azul
        )
        Text(
            label,
            color = Color(0xFF0052CC),  // Cambiar el color del texto a azul
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
