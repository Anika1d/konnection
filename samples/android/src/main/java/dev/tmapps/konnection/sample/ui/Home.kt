package dev.tmapps.konnection.sample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import dev.tmapps.konnection.Konnection
import dev.tmapps.konnection.NetworkConnection
import dev.tmapps.konnection.sample.ui.theme.SampleTheme

@Composable
fun LoadingState(overlayColor: Color = Color.Transparent) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = overlayColor
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun Home(
    initialConnection: NetworkConnection? = null,
    initialIpv4: String? = null,
    initialIpv6: String? = null
) {
    val context = LocalContext.current
    val konnection = Konnection(context)
    val networkState = konnection.observeNetworkConnection().collectAsState(initialConnection)
    val ipv4State = konnection.observeIpv4().collectAsState(initialIpv4)
    val ipv6State = konnection.observeIpv6().collectAsState(initialIpv6)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                colorFilter = ColorFilter.tint(Color.White),
                imageVector = networkState.value.icon,
                modifier = Modifier.size(120.dp),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = networkState.value.message),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = ipv4State.value?.let { "IPv4: $it" } ?: "",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = ipv6State.value?.let { "IPv6: $it" } ?: "",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(name = "loading state")
@Composable
private fun LoadingStatePreview() {
    SampleTheme {
        LoadingState()
    }
}

@Preview(name = "home")
@Composable
private fun HomePreview() {
    SampleTheme {
        Home(
            initialConnection = NetworkConnection.WIFI,
            initialIpv4 = "255.255.255.255",
            initialIpv6 = "xxxx::xxxx:xx:xxxx:xxxx%xxxx"
        )
    }
}
