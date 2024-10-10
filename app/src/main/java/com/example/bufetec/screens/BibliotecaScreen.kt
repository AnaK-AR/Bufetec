package com.example.navtemplate.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import java.text.Normalizer

@Composable
fun BibliotecaScreen(navController: NavController, appViewModel: BibliotecaViewModel = viewModel()) {
    val libraryItems = appViewModel.getLibraryItems()
    var filteredItems by remember { mutableStateOf<List<LibraryItem>?>(null) }
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }

        // Search bar
        item {
            SearchBar(onSearch = { query ->
                Log.d("BibliotecaScreen", "Search executed with query: '$query'")
                if (query.isEmpty()) {
                    filteredItems = null
                } else {
                    val items = libraryItems ?: emptyList()
                    filteredItems = items.filter { item ->
                        val title = item.title ?: ""
                        val normalizedTitle = removeAccents(title).lowercase()
                        val normalizedQuery = removeAccents(query).lowercase()
                        normalizedTitle.contains(normalizedQuery)
                    }
                    Log.d("BibliotecaScreen", "Filtered results: ${filteredItems?.size}")
                    if (filteredItems.isNullOrEmpty()) {
                        Toast.makeText(context, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (filteredItems != null) {
            val items = filteredItems!!
            item {
                Text(
                    text = "Resultados de búsqueda",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (items.isEmpty()) {
                item {
                    Text(
                        text = "No se encontraron resultados.",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            } else {
                val ItemsGrid = items.chunked(2)
                items(ItemsGrid.size) { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (item in ItemsGrid[rowIndex]) {
                            CardItem(
                                navController = navController,
                                item = item,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 8.dp)
                            )
                        }

                        if (ItemsGrid[rowIndex].size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

        } else {
            item {
                CategorySection(
                    title = "Constitución",
                    items = libraryItems.filter { it.title.contains("CONSTITUCIÓN", ignoreCase = true) },
                    navController = navController
                )
            }

            item {
                CategorySection(
                    title = "Leyes",
                    items = libraryItems.filter { it.title.contains("LEY", ignoreCase = true) },
                    navController = navController
                )
            }

            item {
                CategorySection(
                    title = "Códigos",
                    items = libraryItems.filter { it.title.contains("CÓDIGO", ignoreCase = true) },
                    navController = navController
                )
            }

            item {
                CategorySection(
                    title = "Derechos Humanos",
                    items = libraryItems.filter { it.title.contains("DERECHOS", ignoreCase = true) },
                    navController = navController
                )
            }

            item {
                CategorySection(
                    title = "Convenciones",
                    items = libraryItems.filter { it.title.contains("CONVENCIÓN", ignoreCase = true) },
                    navController = navController
                )
            }
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
        modifier = modifier
            .size(165.dp)
            .clickable {
                openPdfInBrowser(context, item.pdfUrl)
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFDCDCDC))
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(130.dp)
                )
            }

            Box(
                modifier = Modifier
                    .background(Color(0xFFF5F5F5))
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// Función para normalizar los textos al momento de Search
fun removeAccents(text: String): String {
    val normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD)
    return normalizedText.replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
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