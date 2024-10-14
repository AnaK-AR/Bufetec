import android.app.DatePickerDialog
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bufetec.model.TimeSlot
import com.example.bufetec.viewmodel.CitasViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun CitasScreen(
    navController: NavController,
    viewModel: CitasViewModel = viewModel(),
    calendlyUrl: String,
    modifier: Modifier = Modifier
) {

    var webViewState by remember { mutableStateOf<WebView?>(null) }

    // Composable que hostea el WebView
    AndroidView(
        factory = { context ->
            CookieManager.getInstance().removeAllCookies(null)
            CookieManager.getInstance().flush()
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true

                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()

                val headers = mapOf("Accept-Language" to "es")

                loadUrl(calendlyUrl, headers)
            }
        },
        modifier = modifier,
        update = { webView ->
            webViewState = webView
        }
    )
}