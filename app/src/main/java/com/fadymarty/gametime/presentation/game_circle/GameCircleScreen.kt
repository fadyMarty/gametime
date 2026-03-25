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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    viewModel: GameCircleViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GameCircleScreen(
        state = state,
        onEvent = viewModel::onEvent
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
                            width = maxWidth.value,
                            height = maxHeight.value
                        )
                    )
                }

                state.circles.forEach { circle ->
                    Box(
                        modifier = Modifier
                            .offset(circle.x.dp, circle.y.dp)
                            .size(circle.diameter.dp)
                            .border(
                                width = circle.borderWidth.dp,
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
                            .size(circle.diameter.dp)
                            .border(
                                width = circle.borderWidth.dp,
                                color = GameTimeTheme.colorScheme.accentInactive,
                                shape = CircleShape
                            )
                    )
                }
            }
            PrimaryButton(
                label = "Surrender",
                onClick = {
                    onEvent(GameCircleEvent.OnCheckClick)
                }
            )
        }
    }
}