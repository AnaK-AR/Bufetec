import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun CitasScreen(
    navController: NavController,
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