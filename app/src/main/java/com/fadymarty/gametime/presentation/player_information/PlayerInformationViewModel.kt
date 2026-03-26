package com.fadymarty.gametime.presentation.player_information

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.fadymarty.gametime.presentation.navigation.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlayerInformationViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerInformationState())
    val state = _state.asStateFlow()

    fun onEvent(event: PlayerInformationEvent) {
        when (event) {
            is PlayerInformationEvent.OnChangeImagePickerVisibility -> {
                println(savedStateHandle.toRoute<Route.PlayerInformation>().id)
                _state.update {
                    it.copy(
                        isImagePickerVisible = event.isVisible
                    )
                }
            }
            is PlayerInformationEvent.OnChangeGalleryVisibility -> {
                _state.update {
                    it.copy(
                        isGalleryVisible = event.isVisible,
                        isImagePickerVisible = false
                    )
                }
            }
            is PlayerInformationEvent.OnChangeCameraVisibility -> {
                _state.update {
                    it.copy(
                        isCameraVisible = event.isVisible,
                        isImagePickerVisible = false
                    )
                }
            }
            is PlayerInformationEvent.OnImageSelected -> {
                _state.update {
                    it.copy(
                        imageUri = event.uri,
                        isGalleryVisible = false,
                        isCameraVisible = false
                    )
                }
            }
            else -> Unit
        }
    }
}