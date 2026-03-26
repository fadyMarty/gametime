package com.fadymarty.gametime.presentation.discover_combats.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.fadymarty.gametime.R
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * Компонент популярного игрока
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 *
 * @param onClick действие при нажатии
 * @param modifier модификатор
 */
@Composable
fun PlayerItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = GameTimeTheme.colorScheme.accent
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(GameTimeTheme.colorScheme.background)
            .clickable(onClick = onClick)
            .padding(start = 11.dp, end = 17.dp),
        horizontalArrangement = Arrangement.spacedBy(6.73.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(37.27.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.img_profile_pic),
            contentDescription = null
        )
        Column {
            Text(
                text = "Scott Brown",
                style = GameTimeTheme.typography.caption2Bold.copy(
                    fontSize = 6.sp,
                    lineHeight = 1.em,
                    letterSpacing = 0.003.em
                )
            )
            Spacer(modifier = Modifier.height(1.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_royal_crown),
                    contentDescription = null,
                    tint = Color(0xFFF4C73E)
                )
                Text(
                    text = "Gold Player",
                    style = GameTimeTheme.typography.caption2Regular.copy(
                        fontSize = 6.sp,
                        lineHeight = 1.em,
                        letterSpacing = (-0.003).em
                    ),
                    color = Color(0xFFF4C73E)
                )
            }
            Text(
                text = buildAnnotatedString {
                    append("Status: ")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF08F403)
                        )
                    ) {
                        append("Online")
                    }
                },
                style = GameTimeTheme.typography.caption2Bold.copy(
                    fontSize = 6.sp,
                    lineHeight = 1.em,
                    letterSpacing = (-0.003).em
                )
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = buildAnnotatedString {
                    append("Category’s: ")
                    withStyle(
                        style = SpanStyle(
                            color = GameTimeTheme.colorScheme.accentInactive
                        )
                    ) {
                        append("Action, Soccer")
                    }
                },
                style = GameTimeTheme.typography.caption2Bold.copy(
                    fontSize = 6.sp,
                    lineHeight = 1.em
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}