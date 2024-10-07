package com.example.navtemplate.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.bufetec.viewmodel.LibraryItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction

@Composable
fun BibliotecaScreen(navController: NavController, appViewModel: BibliotecaViewModel = viewModel()) {
    val libraryItems = appViewModel.getLibraryItems()
    val filteredItems = remember { mutableStateListOf<LibraryItem>() }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Añadir un espacio superior para el encabezado
        Spacer(modifier = Modifier.height(80.dp))

        // Barra de búsqueda
        SearchBar(onSearch = { query ->
            // Clear and update filteredItems based on the search query, ignoring case
            filteredItems.clear()
            filteredItems.addAll(
                libraryItems.filter { it.title.contains(query, ignoreCase = true) }
            )
        })

        Spacer(modifier = Modifier.height(16.dp))

        CategorySection(
            title = "Constitución",
            items = libraryItems.filter { it.title.contains("CONSTITUCIÓN") },
            navController = navController
        )

        CategorySection(
            title = "Leyes",
            items = libraryItems.filter { it.title.contains("LEY") }, // Filtrado
            navController = navController
        )

        CategorySection(
            title = "Códigos",
            items = libraryItems.filter { it.title.contains("CÓDIGO") },
            navController = navController
        )

        CategorySection(
            title = "Derechos Humanos",
            items = libraryItems.filter { it.title.contains("DERECHOS") },
            navController = navController
        )

        CategorySection(
            title = "Convenciones",
            items = libraryItems.filter { it.title.contains("CONVENCIÓN") },
            navController = navController
        )
    }
}

@Composable
fun CategorySection(
    title: String,
    items: List<LibraryItem>,  // título y URL
    navController: NavController // Para navegación
) {
    Column {
        // Título de la categoría
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp)) // Espacio

        // LazyRow para el desplazamiento horizontal de las tarjetas
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(items.size) { index ->
                CardItem(
                    navController = navController,
                    item = items[index],
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun CardItem(navController: NavController, item: LibraryItem, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.size(165.dp).clickable {
            openPdfInBrowser(context, item.pdfUrl)
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier.size(60.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.title, fontSize = 14.sp, maxLines = 2)
        }
    }
}

@Composable
fun SearchBar(
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Buscar") },
        trailingIcon = {
            IconButton(onClick = {
                onSearch(query)  // Trigger search with the current query when the search icon is clicked
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search  // Set the keyboard action to 'Search'
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)  // Trigger search when the "Enter" or "Search" button is pressed on the keyboard
            }
        )
    )
}

// Función para abrir la URL en el navegador
fun openPdfInBrowser(context: android.content.Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // Verificar que haya una aplicación que pueda manejar el Intent
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(
                context,
                "No hay una aplicación para manejar esta acción",
                Toast.LENGTH_SHORT
            ).show()
        }
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "URL inválida o error al abrir el navegador",
            Toast.LENGTH_SHORT
        ).show()
    }
}