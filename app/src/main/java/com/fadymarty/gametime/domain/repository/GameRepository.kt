package com.fadymarty.gametime.domain.repository

import com.fadymarty.gametime.common.util.Result
import com.fadymarty.gametime.domain.model.ImagePiece

interface GameRepository {
    suspend fun getImagePieces(): Result<List<ImagePiece>>
}