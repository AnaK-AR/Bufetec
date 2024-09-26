package com.example.navtemplate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bufetec.viewmodel.BibliotecaViewModel
import com.example.bufetec.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

@Composable
fun BibliotecaScreen(navController: NavController, appViewModel: BibliotecaViewModel = viewModel()) {
    val libraryItems = appViewModel.getLibraryItems()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Añadir un espacio superior para el encabezado
        Spacer(modifier = Modifier.height(65.dp))

        // Barra de búsqueda
        SearchBar()

        Spacer(modifier = Modifier.height(16.dp))

        // Primera categoría: Leyes
        CategorySection(
            title = "Leyes",
            items = libraryItems,
            navController = navController
        )

        // Segunda categoría: Sitios Oficiales
        CategorySection(
            title = "Sitios Oficiales",
            items = listOf(
                "¿Cómo Solicitar una Pensión Alimenticia en México?",
                "¿Cómo Solicitar una Pensión Alimenticia en México?"
            ),
            navController = navController
        )

        // Tercera categoría: Derechos Humanos
        CategorySection(
            title = "Derechos Humanos",
            items = listOf(
                "Protección de Derechos Civiles",
                "Derechos Humanos Internacionales"
            ),
            navController = navController
        )

        // Cuarta categoría: Normas Internacionales
        CategorySection(
            title = "Normas Internacionales",
            items = listOf(
                "Regulaciones Internacionales de Comercio",
                "Normativas Ambientales Globales"
            ),
            navController = navController
        )
    }
}

@Composable
fun CategorySection(title: String, items: List<String>, navController: NavController) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )

    // LazyRow para un desplazamiento horizontal
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Usar la función correcta para iterar los items
        items(items.size) { index ->
            CardItem(navController = navController, text = items[index], modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun CardItem(navController: NavController, text: String, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        modifier = modifier
            .size(width = 200.dp, height = 150.dp)
            .clickable {
                // Navegar a la pantalla de detalle
                navController.navigate("detalle/${text}")
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = text, fontSize = 14.sp)
        }
    }
}

@Composable
fun SearchBar() {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Buscar") }
    )
}