package com.fadymarty.gametime.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import coil3.ImageLoader
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.crossfade
import coil3.toBitmap
import com.fadymarty.gametime.common.util.Result
import com.fadymarty.gametime.domain.model.ImagePiece
import com.fadymarty.gametime.domain.repository.GameRepository

/**
 * Репозиторий для загрузки изображения для игры категории Image
 */
class GameRepositoryImpl(
    private val context: Context,
    private val imageLoader: ImageLoader,
) : GameRepository {

    /**
     * Функция для загрузки изображения и деления его на 9 равных частей
     */
    override suspend fun getImagePieces(): Result<List<ImagePiece>> {
        val request = ImageRequest.Builder(context)
            .data(IMAGE_URL)
            .crossfade(true)
            .build()

        when (val result = imageLoader.execute(request)) {
            is SuccessResult -> {
                val bitmap = result.image.toBitmap()
                val pieceWidth = bitmap.width / 3
                val pieceHeight = bitmap.height / 3

                val pieces = mutableListOf<ImagePiece>()

                for (row in 0 until 3) {
                    for (col in 0 until 3) {
                        val pieceBitmap = Bitmap.createBitmap(
                            bitmap,
                            col * pieceWidth,
                            row * pieceHeight,
                            pieceWidth,
                            pieceHeight
                        )
                        val piece = ImagePiece(
                            index = row * 3 + col,
                            bitmap = pieceBitmap
                        )
                        pieces.add(piece)
                    }
                }

                return Result.Success(pieces)
            }
            is ErrorResult -> {
                Log.e(TAG, "Ошибка — Не удалось загрузить изображение")
                return Result.Failure(result.throwable)
            }
        }
    }

    companion object {
        private const val IMAGE_URL =
            "https://static.wikia.nocookie.net/turma-do-kamil/images/5/53/A5f7322cdf21c4de36c7e2c48c926e4c433fe5e4_hq.jpg/revision/latest?cb=20240814003202&path-prefix=pt-br"
        private const val TAG = "GameImageRepositoryImpl"
    }
}