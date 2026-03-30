package com.fadymarty.gametime.presentation.game_circle

import com.fadymarty.gametime.domain.model.Circle

data class GameCircleState(
    val circles: List<Circle> = emptyList(),
    val collectedCircles: List<Circle> = emptyList(),
    val seconds: Int = 0,
    val isCompleted: Boolean = false,
)