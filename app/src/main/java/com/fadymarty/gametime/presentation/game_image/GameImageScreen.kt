package com.fadymarty.gametime.presentation.game_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fadymarty.gametime.presentation.components.SuccessScreen
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.common.theme.GameTimeTheme
import com.fadymarty.uikit.timer.Timer
import org.koin.compose.viewmodel.koinViewModel
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyGridState

/**
 * Экран игры категории Image
 *
 * Дата создания: 23-03-2026
 * Автор создания: 1
 *
 * @param viewModel ViewModel экрана игры категории Circle
 */
@Composable
fun GameImageRoot(
    onCloseClick: () -> Unit,
    viewModel: GameImageViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GameImageScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                GameImageEvent.OnCloseClick -> {
                    onCloseClick()
                }
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@Composable
private fun GameImageScreen(
    state: GameImageState,
    onEvent: (GameImageEvent) -> Unit,
) {
    val lazyGridState = rememberLazyGridState()
    val reorderableLazyGridState = rememberReorderableLazyGridState(lazyGridState) { from, to ->
        onEvent(GameImageEvent.OnPieceMove(from.index, to.index))
    }

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
                .padding(horizontal = 40.dp)
                .padding(top = 11.dp, bottom = 69.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Game Image",
                style = GameTimeTheme.typography.title2ExtraBold,
                color = GameTimeTheme.colorScheme.accentInactive
            )
            Spacer(modifier = Modifier.height(30.dp))
            Timer(
                seconds = state.seconds
            )
            Spacer(modifier = Modifier.height(25.18.dp))
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Fixed(3),
                state = lazyGridState
            ) {
                items(
                    items = state.imagePieces,
                    key = { it.index }
                ) { piece ->
                    ReorderableItem(
                        reorderableLazyGridState,
                        key = piece.index
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .draggableHandle(
                                    enabled = !state.isCompleted
                                ),
                            bitmap = piece.bitmap.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                modifier = Modifier.padding(horizontal = 40.dp),
                label = "Surrender",
                onClick = {
                    onEvent(GameImageEvent.OnCheckClick)
                }
            )
        }

        if (state.isCompleted) {
            SuccessScreen(
                onCloseClick = {
                    onEvent(GameImageEvent.OnCloseClick)
                },
                title = "You Winner",
                buttonLabel = "Discover combats",
                onButtonClick = {
                    onEvent(GameImageEvent.OnCloseClick)
                }
            )
        }
    }
}

@Preview
@Composable
private fun GameImageScreenPreview() {
    GameTimeTheme {
        GameImageScreen(
            state = GameImageState(),
            onEvent = {}
        )
    }
}