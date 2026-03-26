package com.fadymarty.gametime.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadymarty.gametime.R
import com.fadymarty.gametime.common.util.ObserveAsEvents
import com.fadymarty.gametime.presentation.navigation.Route
import com.fadymarty.uikit.common.theme.GameTimeTheme
import org.koin.compose.viewmodel.koinViewModel

/**
 * Экран Splash
 *
 * Дата создания: 24-03-2026
 * Автор создания: 1
 *
 * @param viewModel ViewModel экрана Splash
 */
@Composable
fun SplashRoot(
    onNavigate: (Route) -> Unit,
    viewModel: SplashViewModel = koinViewModel(),
) {
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is SplashEvent.OnNavigate -> {
                onNavigate(event.route)
            }
        }
    }

    SplashScreen()
}

@Composable
private fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(GameTimeTheme.colorScheme.accent))
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.img_splash_graphics),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(173.91.dp, 100.85.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
                contentDescription = null,
                tint = GameTimeTheme.colorScheme.onAccent
            )
            Spacer(modifier = Modifier.weight(2f))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    GameTimeTheme {
        SplashScreen()
    }
}