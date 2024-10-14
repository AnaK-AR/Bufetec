package com.example.bufetec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bufetec.ui.theme.BufeTecTheme
import com.example.navtemplate.navigation.AppNavHost
import com.example.navtemplate.service.UserService
import com.example.navtemplate.viewmodel.UserViewModel
import com.example.navtemplate.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BufeTecTheme {
                // Usa viewModel() para obtener la instancia de UserViewModel
                val userViewModel: UserViewModel = viewModel(
                    factory = UserViewModelFactory(application, UserService.instance)
                )

                userViewModel.startUser()  // Verifica si el usuario ya está logueado

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(userViewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
