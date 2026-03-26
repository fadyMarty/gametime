package com.fadymarty.gametime.presentation.player_information.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * Компонент категории
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 *
 * @param label текст
 * @param modifier модификатор
 */
@Composable
fun CategoryChip(
    label: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .height(20.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = GameTimeTheme.colorScheme.accent
                ),
                shape = RoundedCornerShape(3.dp)
            )
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = GameTimeTheme.typography.caption2Regular.copy(
                fontSize = 10.sp,
                lineHeight = 1.em,
                letterSpacing = 0.003.em
            )
        )
    }
}