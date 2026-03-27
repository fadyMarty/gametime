package com.fadymarty.gametime.presentation.game_circle

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fadymarty.gametime.presentation.components.SuccessScreen
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.common.theme.GameTimeTheme
import com.fadymarty.uikit.timer.Timer
import org.koin.compose.viewmodel.koinViewModel

/**
 * Экран игры категории Circle
 *
 * @param viewModel ViewModel экрана игры категории Circle
 */
@Composable
fun GameCircleRoot(
    onCloseClick: () -> Unit,
    viewModel: GameCircleViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GameCircleScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                GameCircleEvent.OnCloseClick -> {
                    onCloseClick()
                }
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@Composable
private fun GameCircleScreen(
    state: GameCircleState,
    onEvent: (GameCircleEvent) -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .then(
                    if (state.isCompleted) {
                        Modifier.blur(14.dp)
                    } else Modifier
                )
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 11.dp, bottom = 69.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Game Circle",
                style = GameTimeTheme.typography.title2ExtraBold,
                color = GameTimeTheme.colorScheme.accentInactive
            )
            Spacer(modifier = Modifier.height(30.dp))
            Timer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                seconds = state.seconds
            )
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LaunchedEffect(Unit) {
                    onEvent(
                        GameCircleEvent.OnGenerateCircles(
                            width = maxWidth.value.toInt(),
                            height = maxHeight.value.toInt()
                        )
                    )
                }

                state.circles.forEach { circle ->
                    Box(
                        modifier = Modifier
                            .offset(circle.x.dp, circle.y.dp)
                            .size(circle.config.diameter.dp)
                            .border(
                                width = circle.config.borderWidth.dp,
                                color = GameTimeTheme.colorScheme.accentInactive,
                                shape = CircleShape
                            )
                            .clickable(
                                interactionSource = null,
                                indication = null
                            ) {
                                onEvent(GameCircleEvent.OnCircleClick(circle))
                            }
                    )
                }

                state.collectedCircles.forEach { circle ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(circle.config.diameter.dp)
                            .border(
                                width = circle.config.borderWidth.dp,
                                color = GameTimeTheme.colorScheme.accentInactive,
                                shape = CircleShape
                            )
                    )
                }
            }
            PrimaryButton(
                modifier = Modifier.padding(horizontal = 80.dp),
                label = "Surrender",
                onClick = {
                    onEvent(GameCircleEvent.OnCheckClick)
                }
            )
        }

        if (state.isCompleted) {
            SuccessScreen(
                onCloseClick = {
                    onEvent(GameCircleEvent.OnCloseClick)
                },
                title = "You Winner",
                buttonLabel = "Discover combats",
                onButtonClick = {
                    onEvent(GameCircleEvent.OnCloseClick)
                }
            )
        }
    }
}