package com.fadymarty.gametime.presentation.discover_combats.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.fadymarty.gametime.R
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * Текстовое поле поиска
 *
 * @param state состояния текстового поля
 * @param modifier модификатор
 * @param placeholder подсказка
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun SearchInput(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(41.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = GameTimeTheme.colorScheme.accent
                ),
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = GameTimeTheme.colorScheme.onAccent,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(horizontal = 18.dp),
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = GameTimeTheme.typography.caption2Regular,
        cursorBrush = SolidColor(GameTimeTheme.colorScheme.accentInactive),
        decorator = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.97.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = GameTimeTheme.colorScheme.accentInactive
                )
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (state.text.isEmpty() && placeholder != null) {
                        Text(
                            text = placeholder,
                            style = GameTimeTheme.typography.caption2Regular,
                            color = GameTimeTheme.colorScheme.placeholder
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}