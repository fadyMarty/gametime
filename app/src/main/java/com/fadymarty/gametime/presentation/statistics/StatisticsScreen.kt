package com.fadymarty.gametime.presentation.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.fadymarty.gametime.R
import com.fadymarty.gametime.presentation.statistics.components.PlayedGamesChart
import com.fadymarty.gametime.presentation.statistics.components.ScheduledGamesChart
import com.fadymarty.gametime.presentation.statistics.components.WeekEarningsChart
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
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
            .union(WindowInsets.displayCutout)
            .add(WindowInsets(40.dp, 16.dp, 40.dp))
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier
                            .width(20.dp)
                            .clickable(
                                interactionSource = null,
                                indication = ripple(bounded = false)
                            ) {

                            },
                        imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                        contentDescription = null,
                        tint = GameTimeTheme.colorScheme.accentInactive
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        text = "Statistics",
                        style = GameTimeTheme.typography.title1Extrabold,
                        color = GameTimeTheme.colorScheme.accentInactive
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = GameTimeTheme.colorScheme.accent
                            )
                        )
                        .padding(
                            start = 16.dp,
                            top = 13.dp,
                            end = 23.dp,
                            bottom = 10.dp
                        )
                ) {
                    Text(
                        text = "THIS WEEK EARNINGS",
                        style = GameTimeTheme.typography.caption2Regular,
                        color = GameTimeTheme.colorScheme.onAccent
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(23.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = $$"$240.00",
                            style = GameTimeTheme.typography.title1Extrabold.copy(
                                fontWeight = FontWeight.W700,
                                fontSize = 30.sp,
                                lineHeight = 1.em,
                                letterSpacing = 0.003.em
                            ),
                            color = GameTimeTheme.colorScheme.onAccent
                        )
                        WeekEarningsChart(
                            modifier = Modifier.weight(1f),
                            earnings = listOf(10f, 100f, 10f, 100f, 10f, 100f, 10f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(26.dp))
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Played Games",
                    style = GameTimeTheme.typography.caption2Bold,
                    color = GameTimeTheme.colorScheme.accentInactive
                )
                PlayedGamesChart(
                    circleWinPercentage = 0.25f,
                    imageWinPercentage = 0.5f
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Scheduled Games",
                    style = GameTimeTheme.typography.caption2Bold,
                    color = GameTimeTheme.colorScheme.accentInactive
                )
                Spacer(modifier = Modifier.height(6.dp))
                ScheduledGamesChart(
                    gamesCount = listOf(3, 4, 2, 1, 3, 4, 5)
                )
            }
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