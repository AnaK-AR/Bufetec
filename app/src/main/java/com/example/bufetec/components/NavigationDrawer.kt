import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Cottage
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.navtemplate.viewmodel.UserViewModel

@Composable
fun NavigationDrawer(navController: NavController, appViewModel: UserViewModel, onNavigate: (String) -> Unit) {
    val isUserLogged = appViewModel.isUserLogged

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    ModalDrawerSheet(modifier = Modifier) {
        // HomeScreen
        NavigationDrawerItem(
            label = {
                Row {
                    Icon(imageVector = Icons.Default.Cottage, contentDescription = "Pantalla Principal")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("HomeScreen")
                }
            },
            selected = currentDestination == "home",
            onClick = { onNavigate("home") }
        )


        // Perfil
        NavigationDrawerItem(
            label = {
                Row {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Perfil")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("Perfil")
                }
            },
            selected = currentDestination == "perfil",
            onClick = { onNavigate("perfil") }
        )

        // Biblioteca
        NavigationDrawerItem(
            label = {
                Row {
                    Icon(imageVector = Icons.Default.LibraryBooks, contentDescription = "Biblioteca")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("Biblioteca")
                }
            },
            selected = currentDestination == "biblioteca",
            onClick = { onNavigate("biblioteca") }
        )

        // Recursos Educativos
        NavigationDrawerItem(
            label = {
                Row {
                    Icon(imageVector = Icons.Default.Book, contentDescription = "Recursos Educativos")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("Recursos Educativos")
                }
            },
            selected = currentDestination == "recursos educativos",
            onClick = { onNavigate("recursos educativos") }
        )

        // Citas
        NavigationDrawerItem(
            label = {
                Row {
                    Icon(imageVector = Icons.Default.Event, contentDescription = "Citas")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("Citas")
                }
            },
            selected = currentDestination == "citas",
            onClick = { onNavigate("citas") }
        )
        // Info de Abogados
        NavigationDrawerItem(
            label = {
                Row {
                    Icon(imageVector = Icons.Default.Contacts, contentDescription = "Información de Abogados")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text("Abogados")
                }
            },
            selected = currentDestination == "abogados",
            onClick = { onNavigate("abogados") }
        )

    }
}
