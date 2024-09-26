package com.example.bufetec.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class BibliotecaViewModel : ViewModel() {

    // Lista de elementos para la biblioteca
    private val _libraryItems = mutableStateListOf<String>()

    // Función para obtener los elementos de la biblioteca
    fun getLibraryItems(): SnapshotStateList<String> {
        if (_libraryItems.isEmpty()) {
            _libraryItems.addAll(
                listOf(
                    "¿Cómo Solicitar una Pensión Alimenticia en México?",
                    "¿Qué Hacer si No Cumple la Obligación de Alimentos?",
                    "Protección de Derechos Civiles",
                    "Leyes de Amparo en México"
                )
            )
        }
        return _libraryItems
    }
}