package com.fadymarty.gametime.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadymarty.gametime.R
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.common.theme.GameTimeTheme

/**
 * Экран Success
 *
 * Дата создания: 26-03-2026
 * Автор создания: 1
 */
@Composable
fun SuccessScreen(
    onCloseClick: () -> Unit,
    title: String,
    buttonLabel: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = GameTimeTheme.colorScheme.accentInactive.copy(alpha = 0.7f)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(153.dp))
            Box(
                modifier = Modifier
                    .size(138.dp)
                    .border(
                        width = 3.dp,
                        color = GameTimeTheme.colorScheme.onAccent,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = GameTimeTheme.colorScheme.accent
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.width(45.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_checkmark),
                    contentDescription = null,
                    tint = GameTimeTheme.colorScheme.onAccent
                )
            }
            Spacer(modifier = Modifier.height(37.dp))
            Text(
                modifier = Modifier.padding(horizontal = 76.dp),
                text = title,
                style = GameTimeTheme.typography.title1Extrabold,
                textAlign = TextAlign.Center,
                color = GameTimeTheme.colorScheme.onAccent
            )
            if (description != null) {
                Spacer(modifier = Modifier.height(21.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 82.dp),
                    text = description,
                    style = GameTimeTheme.typography.captionRegular,
                    textAlign = TextAlign.Center,
                    color = GameTimeTheme.colorScheme.onAccent
                )
            }
            Spacer(modifier = Modifier.height(33.dp))
            PrimaryButton(
                modifier = Modifier.padding(horizontal = 82.dp),
                label = buttonLabel,
                onClick = onButtonClick
            )
        }
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 68.dp, end = 44.dp)
                .size(26.dp)
                .clickable(
                    interactionSource = null,
                    indication = ripple(bounded = false),
                    onClick = onCloseClick
                ),
            imageVector = ImageVector.vectorResource(R.drawable.ic_close),
            contentDescription = null,
            tint = GameTimeTheme.colorScheme.onAccent
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessScreenPreview() {
    GameTimeTheme {
        SuccessScreen(
            onCloseClick = {},
            title = "Published Successful",
            buttonLabel = "Statistics",
            onButtonClick = {},
            description = "Wanna change/edit your scheduled game before it begins?"
        )
    }
}