package com.example.bufetec.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Video(
    val id: String,
    val title: String
)

data class Articulo(
    val title: String
)

class RecursosViewModel : ViewModel() {
    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    private val _articulos = MutableLiveData<List<Articulo>>()
    val articulos: LiveData<List<Articulo>> get() = _articulos

    init {
        _videos.value = listOf(
            Video("TiYXl7BC5eA", "Oral de alimentos"),
            Video("4zv7DECqaEs", "Juicio de Convivencia"),
            Video("92JpKyiEHJw", "Rectificación de actas"),
            Video("HPMkE6kZdgQ", "Divorcio"),
        )
        _articulos.value = listOf(
            Articulo("Artículo 1"),
            Articulo("Artículo 2"),
            Articulo("Artículo 3"),
            Articulo("Artículo 4"),
        )
    }

    fun addVideo(video: Video) {
        _videos.value = _videos.value?.plus(video) ?: listOf(video)
    }

    fun addArticulo(articulos: Articulo) {
        _articulos.value = _articulos.value?.plus(articulos) ?: listOf(articulos)
    }
}
