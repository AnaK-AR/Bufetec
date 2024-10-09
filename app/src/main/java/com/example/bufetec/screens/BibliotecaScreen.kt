package com.example.navtemplate.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun BibliotecaScreen(navController: NavController, appViewModel: BibliotecaViewModel = viewModel()) {
    val libraryItems = appViewModel.getLibraryItems()
    var filteredItems by remember { mutableStateOf<List<LibraryItem>?>(null) } // null indica sin filtro
    val scrollState = rememberScrollState()
    val context = LocalContext.current

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
            Log.d("BibliotecaScreen", "Búsqueda ejecutada con query: '$query'")
            if (query.isEmpty()) {
                filteredItems = null  // Sin filtro
            } else {
                val items = libraryItems ?: emptyList()
                // Filtra los items basados en la consulta, ignorando mayúsculas y minúsculas
                filteredItems = items.filter { it.title?.contains(query, ignoreCase = true) == true }
                Log.d("BibliotecaScreen", "Resultados filtrados: ${filteredItems?.size}")
                if (filteredItems.isNullOrEmpty()) {
                    Toast.makeText(context, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
                }
            }
        })

        Spacer(modifier = Modifier.height(16.dp))

        filteredItems?.let { items ->
            // Mostrar solo resultados filtrados
            Text(
                text = "Resultados de búsqueda",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            if (items.isEmpty()) {
                Text(text = "No se encontraron resultados.", modifier = Modifier.padding(8.dp))
            } else {
                LazyColumn {
                    items(filteredItems!!.size) { item ->
                        CardItem(
                            navController = navController,
                            item = filteredItems!![item],
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                    }
                }
            }
        } ?: run {
            // Mostrar las categorías completas de la biblioteca
            CategorySection(
                title = "Constitución",
                items = libraryItems.filter { it.title.contains("CONSTITUCIÓN", ignoreCase = true) },
                navController = navController
            )

            CategorySection(
                title = "Leyes",
                items = libraryItems.filter { it.title.contains("LEY", ignoreCase = true) }, // Filtrado
                navController = navController
            )

            CategorySection(
                title = "Códigos",
                items = libraryItems.filter { it.title.contains("CÓDIGO", ignoreCase = true) },
                navController = navController
            )

            CategorySection(
                title = "Derechos Humanos",
                items = libraryItems.filter { it.title.contains("DERECHOS", ignoreCase = true) },
                navController = navController
            )

            CategorySection(
                title = "Convenciones",
                items = libraryItems.filter { it.title.contains("CONVENCIÓN", ignoreCase = true) },
                navController = navController
            )
        }
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
        singleLine = true,  // Evita múltiples líneas
        trailingIcon = {
            IconButton(onClick = {
                onSearch(query) // Ejecuta la búsqueda
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query) // Ejecuta la búsqueda
            }
        )
    )
}

// Función para abrir la URL en el navegador
fun openPdfInBrowser(context: android.content.Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "URL inválida o error al abrir el navegador",
            Toast.LENGTH_SHORT
        ).show()
    }
}