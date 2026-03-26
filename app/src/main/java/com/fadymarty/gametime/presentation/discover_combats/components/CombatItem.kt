package com.fadymarty.gametime.presentation.discover_combats.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.fadymarty.gametime.R
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * Карточка игры
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 *
 * @param modifier модификатор
 * @param onClick действие при нажатии
 */
@Composable
fun CombatItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .height(64.dp)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = GameTimeTheme.colorScheme.accent
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(start = 10.dp, end = 9.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CombatPlayerItem(isHost = true)
        Text(
            text = "VS",
            style = GameTimeTheme.typography.title2ExtraBold,
            color = GameTimeTheme.colorScheme.accentInactive
        )
        CombatPlayerItem(isHost = false)
        CombatInfoItem(
            title = "Game  Name:",
            description = "Halo 5"
        )
        CombatInfoItem(
            title = "Status:",
            description = "Open"
        )
        CombatInfoItem(
            title = "Winning Price:",
            description = $$"$4,000"
        )
    }
}

@Composable
private fun CombatPlayerItem(
    isHost: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.73.dp)
    ) {
        Box {
            Image(
                modifier = Modifier
                    .size(27.27.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.img_profile_pic),
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .offset(
                        x = if (isHost) (-11).dp else 17.dp,
                        y = (-4).dp
                    )
                    .size(20.dp, 9.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(GameTimeTheme.colorScheme.accentInactive),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isHost) "Host" else "Guest",
                    style = GameTimeTheme.typography.caption2Regular.copy(
                        fontSize = 4.sp,
                        lineHeight = 1.em
                    ),
                    color = GameTimeTheme.colorScheme.onAccent
                )
            }
        }
        Text(
            text = "Scott Brown",
            style = GameTimeTheme.typography.caption2Bold.copy(
                fontSize = 6.sp,
                lineHeight = 1.em
            )
        )
    }
}

@Composable
private fun CombatInfoItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = title,
            style = GameTimeTheme.typography.caption2Regular.copy(
                fontSize = 6.sp,
                lineHeight = 1.em
            )
        )
        Text(
            text = description,
            style = GameTimeTheme.typography.caption2Bold.copy(
                fontSize = 6.sp,
                lineHeight = 1.em
            ),
            color = GameTimeTheme.colorScheme.accentInactive
        )
    }
}