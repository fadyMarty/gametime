package com.fadymarty.gametime.presentation.player_information

sealed interface PlayerInformationEvent {
    data object OnBackClick : PlayerInformationEvent
    data class OnChangeImagePickerVisibility(val isVisible: Boolean) : PlayerInformationEvent
    data class OnChangeGalleryVisibility(val isVisible: Boolean) : PlayerInformationEvent
    data class OnChangeCameraVisibility(val isVisible: Boolean) : PlayerInformationEvent
    data class OnImageSelected(val uri: String) : PlayerInformationEvent
}