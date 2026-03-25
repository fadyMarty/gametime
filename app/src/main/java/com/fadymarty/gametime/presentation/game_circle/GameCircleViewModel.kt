package com.fadymarty.gametime.presentation.game_circle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.gametime.domain.model.Circle
import com.fadymarty.gametime.domain.model.circleTemplates
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * ViewModel экрана игры категории Circle
 *
 * Дата создания: 24-03-2026
 * Автор создания: 1
 */
class GameCircleViewModel : ViewModel() {

    private val _state = MutableStateFlow(GameCircleState())
    val state = _state.asStateFlow()

    private var timerJob: Job? = null

    fun onEvent(event: GameCircleEvent) {
        when (event) {
            is GameCircleEvent.OnGenerateCircles -> {
                _state.value = GameCircleState(
                    circles = circleTemplates.map { template ->
                        val maxX = event.width.toInt() - template.diameter
                        val maxY = event.height.toInt() - template.diameter
                        Circle(
                            diameter = template.diameter,
                            borderWidth = template.borderWidth,
                            x = Random.nextInt(maxX),
                            y = Random.nextInt(maxY)
                        )
                    }
                )
                startTimer()
            }
            is GameCircleEvent.OnCircleClick -> {
                val maxDiameterCircle = _state.value.circles.maxBy { it.diameter }
                if (event.circle == maxDiameterCircle) {
                    _state.update {
                        it.copy(
                            circles = it.circles
                                .toMutableList()
                                .apply {
                                    remove(event.circle)
                                },
                            collectedCircles = it.collectedCircles
                                .toMutableList()
                                .apply {
                                    add(event.circle)
                                }
                        )
                    }
                }
            }
            GameCircleEvent.OnCheckClick -> {
                _state.update {
                    it.copy(
                        isSolved = _state.value.circles.isEmpty()
                    )
                }
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _state.value = _state.value.copy(
                    seconds = _state.value.seconds + 1
                )
            }
        }
    }
}