package com.fadymarty.gametime.presentation.player_information

data class PlayerInformationState(
    val imageUri: String? = null,
    val isImagePickerVisible: Boolean = false,
    val isCameraVisible: Boolean = false,
    val isGalleryVisible: Boolean = false,
)
