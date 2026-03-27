package com.fadymarty.gametime.presentation.statistics.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.fadymarty.uikit.common.theme.GameTimePalette

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
    Canvas(
        modifier = modifier.height(45.dp)
    ) {
        val maxValue = earnings.max()
        val minValue = earnings.min()
        val range = maxValue - minValue
        val xSpacing = size.width / (earnings.size - 1)

        val points = earnings.mapIndexed { index, value ->
            Offset(
                x = index * xSpacing,
                y = size.height - ((value - minValue) / range * size.height),
            )
        }

        val path = Path().apply {
            moveTo(points.first().x, points.first().y)

            for (i in 0 until points.size - 1) {
                val current = points[i]
                val next = points[i + 1]
                val controlX = (current.x + next.x) / 2f

                cubicTo(
                    x1 = controlX,
                    y1 = current.y,
                    x2 = controlX,
                    y2 = next.y,
                    x3 = next.x,
                    y3 = next.y,
                )
            }
        }

        drawPath(
            path = path,
            color = GameTimePalette.White,
            style = Stroke(
                width = 2.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
            ),
        )
    }
}