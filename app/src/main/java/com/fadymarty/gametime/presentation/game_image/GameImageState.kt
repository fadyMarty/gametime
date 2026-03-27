package com.fadymarty.gametime.presentation.game_image

import com.fadymarty.gametime.domain.model.ImagePiece

data class GameImageState(
    val imagePieces: List<ImagePiece> = emptyList(),
    val isCompleted: Boolean = false,
    val seconds: Int = 0,
)