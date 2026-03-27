package com.fadymarty.gametime.presentation.game_image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.gametime.common.util.onSuccess
import com.fadymarty.gametime.domain.use_case.game.GetImagePiecesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel экрана игры Image
 *
 * @param getImagePiecesUseCase UseCase для получения картинки по кусочкам
 */
class GameImageViewModel(
    private val getImagePiecesUseCase: GetImagePiecesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(GameImageState())
    val state = _state.asStateFlow()

    private var timerJob: Job? = null
    private var isCompletedJob: Job? = null

    init {
        getImagePieces()
    }

    fun onEvent(event: GameImageEvent) {
        when (event) {
            is GameImageEvent.OnPieceMove -> {
                _state.update {
                    it.copy(
                        imagePieces = _state.value.imagePieces
                            .toMutableList()
                            .apply {
                                add(event.to, removeAt(event.from))
                            }
                    )
                }
                isCompleted()
            }
            GameImageEvent.OnCheckClick -> {
                isCompleted()
            }
            else -> Unit
        }
    }

    private fun getImagePieces() {
        viewModelScope.launch {
            getImagePiecesUseCase()
                .onSuccess { imagePieces ->
                    _state.update {
                        it.copy(
                            imagePieces = imagePieces.shuffled()
                        )
                    }
                    startTimer()
                }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000L)
                _state.update {
                    it.copy(
                        seconds = it.seconds + 1
                    )
                }
            }
        }
    }

    private fun isCompleted() {
        isCompletedJob?.cancel()
        isCompletedJob = viewModelScope.launch {
            val isCompleted = _state.value.imagePieces
                .mapIndexed { index, piece ->
                    piece.index == index
                }.all { it }
            if (isCompleted) {
                timerJob?.cancel()
            }
            delay(2000L)
            _state.update {
                it.copy(
                    isCompleted = isCompleted
                )
            }

        }
    }
}