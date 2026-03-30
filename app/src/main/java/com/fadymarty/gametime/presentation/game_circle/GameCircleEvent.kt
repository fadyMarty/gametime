package com.fadymarty.gametime.presentation.game_circle

sealed interface GameCircleEvent {
    data class OnGenerateCircles(val areaWidth: Int, val areaHeight: Int) : GameCircleEvent
    data class OnCircleDrop(val id: Int) : GameCircleEvent
    data object OnCheckClick : GameCircleEvent
    data object OnCloseClick : GameCircleEvent
}