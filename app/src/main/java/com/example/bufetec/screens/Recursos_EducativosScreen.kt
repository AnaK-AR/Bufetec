package com.example.bufetec.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bufetec.viewmodel.Articulo
import com.example.bufetec.viewmodel.RecursosViewModel
import com.example.bufetec.viewmodel.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import androidx.compose.foundation.lazy.grid.items

@Composable
fun Recursos_EducativosScreen(
    navController: NavController,
    appViewModel: RecursosViewModel = viewModel()
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Obtener los videos y articulos 
    val videos by appViewModel.videos.observeAsState(emptyList())
    val articulos by appViewModel.articulos.observeAsState(emptyList())
    
    // Tabs
    val tabs = listOf("Videos", "Artículos")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        // TabRow para switch entre las pantallas Videos and Articles
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, text ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text) }
                )
            }
        }
        
        when (selectedTabIndex) {
            0 -> VideoList(videos = videos) // Show videos
            1 -> ArticulosContent(navController = navController, articulos = articulos) // Show articles
        }
    }
}

@Composable
fun VideoList(videos: List<Video>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(videos) { video ->
            Text(
                text = video.title,
                modifier = Modifier.padding(8.dp),
            )
            YouTubePlayer(videoId = video.id)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun YouTubePlayer(videoId: String, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = { ctx ->
            YouTubePlayerView(ctx).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                })
                lifecycleOwner.lifecycle.addObserver(this)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        update = { view ->
            // Handle updates if necessary
        }
    )

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                // Handle any necessary cleanup when the Composable is destroyed
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun ArticulosContent(navController: NavController, articulos: List<Articulo>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(articulos) { articulo ->
            ArticuloCard(title = articulo.title, navController = navController)
        }
    }
}

@Composable
fun ArticuloCard(title: String, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable {
                navController.navigate("articuloDetailScreen/$title")
            },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, color = Color.Black)
        }
    }
}
