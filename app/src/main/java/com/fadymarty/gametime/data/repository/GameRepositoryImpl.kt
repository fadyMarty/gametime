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
                val size = minOf(bitmap.width, bitmap.height)
                val xStart = (bitmap.width - size) / 2
                val yStart = (bitmap.height - size) / 2

                val squareBitmap = Bitmap.createBitmap(bitmap, xStart, yStart, size, size)

                val pieceSize = squareBitmap.width / 3
                val pieces = mutableListOf<ImagePiece>()

                for (row in 0 until 3) {
                    for (col in 0 until 3) {
                        val pieceBitmap = Bitmap.createBitmap(
                            squareBitmap,
                            col * pieceSize,
                            row * pieceSize,
                            pieceSize,
                            pieceSize
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
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRn74k9jrdLZiWw0dCfb06gfj7SzsJbSBR0cQ&s"
        private const val TAG = "GameImageRepositoryImpl"
    }
}