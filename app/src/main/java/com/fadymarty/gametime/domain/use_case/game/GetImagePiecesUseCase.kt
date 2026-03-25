package com.fadymarty.gametime.domain.use_case.game

import com.fadymarty.gametime.common.util.Result
import com.fadymarty.gametime.domain.model.ImagePiece
import com.fadymarty.gametime.domain.repository.GameRepository

class GetImagePiecesUseCase(
    private val gameRepository: GameRepository,
) {
    suspend operator fun invoke(): Result<List<ImagePiece>> {
        return gameRepository.getImagePieces()
    }
}