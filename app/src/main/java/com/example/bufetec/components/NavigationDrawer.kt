package com.example.navtemplate.components

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.navtemplate.viewmodel.UserViewModel

@Composable
fun NavigationDrawer(navController: NavController, appViewModel: UserViewModel, onNavigate: (String) -> Unit) {
    val isUserLogged = appViewModel.isUserLogged
// Obtener el estado del back stack del NavController

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    ModalDrawerSheet(modifier = Modifier) {

        NavigationDrawerItem(
            label = { Text("Perfil") },
            selected = currentDestination == "perfil",
            onClick = { onNavigate("perfil") }
        )
        NavigationDrawerItem(
            label = { Text("Biblioteca") },
            selected = currentDestination == "biblioteca",
            onClick = { onNavigate("biblioteca") }
        )
        NavigationDrawerItem(
            label = { Text("Recursos Educativos") },
            selected = currentDestination == "recursos educativos",
            onClick = { onNavigate("recursos educativos") }
        )
        NavigationDrawerItem(
            label = { Text("Citas") },
            selected = currentDestination == "citas",
            onClick = { onNavigate("citas") }
        )


    }
}