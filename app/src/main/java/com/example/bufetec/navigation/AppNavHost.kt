package com.example.navtemplate.navigation

import CitasScreen
import NavigationDrawer
import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bufetec.screens.PerfilScreen
import com.example.bufetec.screens.Recursos_EducativosScreen
import com.example.bufetec.viewmodel.BibliotecaViewModel
import com.example.navtemplate.screens.AbogadosInfoScreen
import com.example.navtemplate.screens.BibliotecaScreen
import com.example.navtemplate.screens.HomeScreen
import com.example.navtemplate.screens.LoginScreen
import com.example.navtemplate.screens.RegisterScreen
import com.example.navtemplate.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(appViewModel: UserViewModel, padding: Modifier) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Crear un solo NavHost para manejar todas las rutas
    NavHost(
        navController = navController,
        startDestination = if (appViewModel.isUserLogged) "home" else "login"
    ) {
        composable("login") {
            LoginScreen(navController, appViewModel)
        }
        composable("register") {
            RegisterScreen(navController,appViewModel)
        }
        composable("home") {
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
                        HomeScreen(navController, appViewModel)
                    }
                )
            }
        }

        composable("biblioteca") {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        NavigationDrawer(navController,appViewModel) { destination ->
                            scope.launch { drawerState.close() }
                            navController.navigate(destination)
                        }
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Biblioteca") },
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
                        BibliotecaScreen(navController,BibliotecaViewModel())
                    }
                )
            }
        }
        composable("perfil") {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        NavigationDrawer(navController,appViewModel) { destination ->
                            scope.launch { drawerState.close() }
                            navController.navigate(destination)
                        }
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Perfil") },
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
                        PerfilScreen(navController, appViewModel)
                    }
                )
            }
        }
        composable("recursos educativos") {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        NavigationDrawer(navController,appViewModel) { destination ->
                            scope.launch { drawerState.close() }
                            navController.navigate(destination)
                        }
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Recursos Educativos") },
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
                        Recursos_EducativosScreen(navController, appViewModel)
                    }
                )
            }
        }
        composable("citas") {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        NavigationDrawer(navController,appViewModel) { destination ->
                            scope.launch { drawerState.close() }
                            navController.navigate(destination)
                        }
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Citas") },
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
                        CitasScreen(navController, calendlyUrl = "https://calendly.com/bufeteclb/30min?locale=es")
                    }
                )
            }
        }
        composable("abogados") {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        NavigationDrawer(navController,appViewModel) { destination ->
                            scope.launch { drawerState.close() }
                            navController.navigate(destination)
                        }
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Abogados") },
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
                        AbogadosInfoScreen(navController, appViewModel)
                    }
                )
            }
        }

    }
}