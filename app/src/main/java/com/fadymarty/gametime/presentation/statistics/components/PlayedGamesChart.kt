package com.fadymarty.gametime.presentation.statistics.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadymarty.uikit.common.theme.GameTimeTheme

@Composable
fun PlayedGamesChart(
    circleWinPercentage: Float,
    imageWinPercentage: Float,
    modifier: Modifier = Modifier,
) {
    val averageWinPercentage = (circleWinPercentage + imageWinPercentage) / 2

    Box(
        modifier = modifier.size(250.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(150.dp)) {
            drawCircle(
                color = Color(0xFFBDBDBD),
                alpha = 0.2f,
                style = Stroke(width = 15.dp.toPx())
            )
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF22E63),
                        Color(0xFFF22E63).copy(alpha = 0.5f)
                    )
                ),
                startAngle = 330f,
                sweepAngle = 360f * imageWinPercentage,
                useCenter = false,
                style = Stroke(width = 45.dp.toPx())
            )
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFD2EFFF),
                        Color(0xFF5FB1DF)
                    )
                ),
                startAngle = 270f,
                sweepAngle = 360f * circleWinPercentage,
                useCenter = false,
                style = Stroke(width = 30.dp.toPx())
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "%.1f".format(averageWinPercentage * 100),
                style = GameTimeTheme.typography.captionSemibold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.alpha(0.2f),
                text = "%",
                style = GameTimeTheme.typography.caption2Bold,
                color = GameTimeTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlayedGamesChartPreview() {
    GameTimeTheme {
        PlayedGamesChart(
            circleWinPercentage = 0.25f,
            imageWinPercentage = 0.5f
        )
    }
}