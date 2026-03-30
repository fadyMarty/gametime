package com.fadymarty.gametime.presentation.game_circle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.gametime.domain.model.Circle
import com.fadymarty.gametime.domain.model.circleConfigs
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
    private var isCompletedJob: Job? = null

    fun onEvent(event: GameCircleEvent) {
        when (event) {
            is GameCircleEvent.OnGenerateCircles -> {
                val circles = circleConfigs.mapIndexed { index, config ->
                    Circle(
                        id = index,
                        config = config,
                        x = Random.nextInt(event.areaWidth - config.diameter),
                        y = Random.nextInt(event.areaHeight - config.diameter)
                    )
                }
                _state.update {
                    it.copy(
                        circles = circles
                    )
                }
                startTimer()
            }
            is GameCircleEvent.OnCircleDrop -> {
                val circle = _state.value.circles.first { it.id == event.id }
                val maxDiameterCircle = _state.value.circles.maxByOrNull { it.config.diameter }
                if (circle == maxDiameterCircle) {
                    _state.update {
                        val circles = it.circles.toMutableList().apply {
                            remove(circle)
                        }
                        val collectedCircles = it.collectedCircles.toMutableList().apply {
                            add(circle)
                        }
                        it.copy(
                            circles = circles,
                            collectedCircles = collectedCircles
                        )
                    }
                    isCompleted()
                }
            }
            GameCircleEvent.OnCheckClick -> {
                isCompleted()
            }
            else -> Unit
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

    private fun isCompleted() {
        isCompletedJob?.cancel()
        isCompletedJob = viewModelScope.launch {
            val isCompleted = _state.value.circles.isEmpty()
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