package com.fadymarty.gametime.presentation.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * Экран статистики
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun StatisticsRoot() {
    StatisticsScreen()
}

@Composable
private fun StatisticsScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun StatisticsPreview() {
    GameTimeTheme {
        StatisticsScreen()
    }
}