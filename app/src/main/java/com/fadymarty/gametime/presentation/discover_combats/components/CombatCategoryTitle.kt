package com.fadymarty.gametime.presentation.discover_combats.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.fadymarty.gametime.R
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * Заголовок категории игр
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 *
 * @param
 */
@Composable
fun CombatCategoryTitle(
    title: String,
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = GameTimeTheme.typography.caption2Bold,
            color = GameTimeTheme.colorScheme.accentInactive
        )
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = onViewAllClick
                ),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "View All",
                style = GameTimeTheme.typography.caption2Regular
            )
            Icon(
                modifier = Modifier.width(7.2.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_access),
                contentDescription = null,
                tint = GameTimeTheme.colorScheme.accentInactive
            )
        }
    }
}