package com.fadymarty.gametime.presentation.game_circle

import com.fadymarty.gametime.domain.model.Circle

sealed interface GameCircleEvent {
    data class OnGenerateCircles(val width: Int, val height: Int) : GameCircleEvent
    data class OnCircleClick(val circle: Circle) : GameCircleEvent
    data object OnCheckClick : GameCircleEvent
}