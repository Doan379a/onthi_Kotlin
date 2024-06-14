package com.example.doandtph42307.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.doandtph42307.ROUTER_SCREEN_NAME
import kotlinx.coroutines.delay

@Composable
fun WellcomScreen(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://th.bing.com/th/id/OIP.t9SH9ejMCc00geYtcVVQKwHaFT?rs=1&pid=ImgDetMain"),
            contentDescription = "",
            modifier = Modifier.size(128.dp), contentScale = ContentScale.Crop
        )
        Text(text = "doandtph42307", style = MaterialTheme.typography.titleLarge)
    }
    LaunchedEffect(Unit) {
        delay(3000L)
        navController.navigate(ROUTER_SCREEN_NAME.HOME.name) {
            popUpTo(ROUTER_SCREEN_NAME.WELCOME.name) { inclusive = true }
        }
    }
}