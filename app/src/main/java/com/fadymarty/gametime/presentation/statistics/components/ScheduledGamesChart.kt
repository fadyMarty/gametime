package com.fadymarty.gametime.presentation.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * График статистики созданных игр
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 *
 * @param gamesCount количество игр созданных в каждый день недели
 * @param modifier модификатор
 */
@Composable
fun ScheduledGamesChart(
    gamesCount: List<Int>,
    modifier: Modifier = Modifier,
) {
    val days = remember {
        listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")
    }
    val maxGamesCount = gamesCount.max()
    val data = gamesCount.map { count ->
        count.toFloat() / maxGamesCount
    }

    Box(
        modifier = modifier.size(250.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.width(200.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            data.forEachIndexed { index, value ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .width(20.dp)
                            .height(114.dp)
                            .clip(RoundedCornerShape(46.dp))
                            .background(Color(0xFFECECEC)),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(value)
                                .clip(RoundedCornerShape(46.dp))
                                .background(Color(0xFFFF6978))
                        )
                    }
                    Text(
                        modifier = Modifier.alpha(0.34f),
                        text = days[index],
                        style = GameTimeTheme.typography.caption2Regular,
                        color = Color(0xFF1D2125),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScheduledGamesChartPreview() {
    GameTimeTheme {
        ScheduledGamesChart(
            gamesCount = listOf(3, 4, 2, 1, 3, 4, 5)
        )
    }
}