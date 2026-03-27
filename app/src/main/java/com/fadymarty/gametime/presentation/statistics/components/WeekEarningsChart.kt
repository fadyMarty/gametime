package com.fadymarty.gametime.presentation.statistics.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.fadymarty.uikit.common.theme.GameTimePalette
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * График статистики суммы заработанных баллов
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 *
 * @param earnings список заработанных баллов за неделю
 * @param modifier модификатор
 */
@Composable
fun WeekEarningsChart(
    earnings: List<Float>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
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
            Canvas(
                modifier = Modifier
                    .weight(1f)
                    .height(45.dp)
            ) {
                val maxValue = earnings.max()
                val minValue = earnings.min()
                val range = maxValue - minValue
                val xStep = size.width / (earnings.size - 1)

                val points = earnings.mapIndexed { index, value ->
                    Offset(
                        x = index * xStep,
                        y = size.height - ((value - minValue) / range * size.height)
                    )
                }

                val path = Path().apply {
                    moveTo(
                        x = points.first().x,
                        y = points.first().y
                    )

                    for (index in 0 until points.size - 1) {
                        val current = points[index]
                        val next = points[index + 1]
                        val controlX = (current.x + next.x) / 2f

                        cubicTo(
                            x1 = controlX,
                            y1 = current.y,
                            x2 = controlX,
                            y2 = next.y,
                            x3 = next.x,
                            y3 = next.y
                        )
                    }
                }

                drawPath(
                    path = path,
                    color = GameTimePalette.White,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WeekEarningPreview() {
    GameTimeTheme {
        WeekEarningsChart(
            modifier = Modifier.fillMaxWidth(),
            earnings = listOf(20f, 90f, 40f, 80f, 10f, 90f, 30f)
        )
    }
}