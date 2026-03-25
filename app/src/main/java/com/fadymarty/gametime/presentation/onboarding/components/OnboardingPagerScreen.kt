package com.fadymarty.gametime.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadymarty.uikit.common.theme.GameTimeTheme
import com.fadymarty.gametime.presentation.onboarding.OnboardingPage

/**
 * Приветственный экран
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 *
 * @param page данные страницы
 */
@Composable
fun OnboardingPagerScreen(
    page: OnboardingPage,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(page.image),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 75.dp)
        ) {
            Text(
                text = page.title,
                style = GameTimeTheme.typography.title1Extrabold,
                textAlign = TextAlign.Center,
                color = GameTimeTheme.colorScheme.accentInactive
            )
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = page.description,
                style = GameTimeTheme.typography.captionRegular,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun OnboardingPagerScreenPreview() {
    GameTimeTheme {
        OnboardingPagerScreen(
            page = OnboardingPage.First
        )
    }
}