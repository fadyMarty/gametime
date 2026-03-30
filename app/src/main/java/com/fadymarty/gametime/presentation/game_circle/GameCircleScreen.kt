package com.fadymarty.gametime.presentation.game_circle

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fadymarty.gametime.presentation.components.SuccessScreen
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.common.theme.GameTimePalette
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

@OptIn(ExperimentalFoundationApi::class)
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
                            areaWidth = maxWidth.value.toInt(),
                            areaHeight = maxHeight.value.toInt()
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(150.dp)
                        .dragAndDropTarget(
                            shouldStartDragAndDrop = { event ->
                                event
                                    .mimeTypes()
                                    .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                            },
                            target = remember {
                                object : DragAndDropTarget {
                                    override fun onDrop(event: DragAndDropEvent): Boolean {
                                        val circleId = event.toAndroidDragEvent()
                                            .clipData
                                            ?.getItemAt(0)
                                            ?.text
                                            ?.toString()
                                            ?.toInt()
                                        onEvent(GameCircleEvent.OnCircleDrop(circleId!!))
                                        return true
                                    }
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    state.collectedCircles.forEach { circle ->
                        Box(
                            modifier = Modifier
                                .size(circle.config.diameter.dp)
                                .border(
                                    width = circle.config.borderWidth.dp,
                                    color = GameTimeTheme.colorScheme.accentInactive,
                                    shape = CircleShape
                                )
                        )
                    }
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
                            .dragAndDropSource(
                                drawDragDecoration = {
                                    val borderWidth = circle.config.borderWidth.dp.toPx()
                                    drawCircle(
                                        color = GameTimePalette.AccentInactive,
                                        radius = (circle.config.diameter.dp.toPx() / 2) - borderWidth / 2,
                                        style = Stroke(borderWidth)
                                    )
                                }
                            ) { _ ->
                                DragAndDropTransferData(
                                    clipData = ClipData.newPlainText(
                                        "circle_id",
                                        circle.id.toString()
                                    )
                                )
                            }
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