package com.fadymarty.gametime.presentation.game_image

sealed interface GameImageEvent {
    data class OnPieceMove(val from: Int, val to: Int) : GameImageEvent
    data object OnCheckClick : GameImageEvent
}