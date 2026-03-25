package com.fadymarty.gametime.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.gametime.domain.use_case.settings.SaveOnboardingStateUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * ViewModel привественных экранов
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 *
 * @param saveOnboardingStateUseCase UseCase для сохранения состояния того, пройдены привественные экраны или нет
 */
class OnboardingViewModel(
    private val saveOnboardingStateUseCase: SaveOnboardingStateUseCase,
) : ViewModel() {

    private val eventChannel = Channel<OnboardingEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.OnSaveOnboardingState -> {
                viewModelScope.launch {
                    saveOnboardingStateUseCase(true)
                    eventChannel.send(OnboardingEvent.OnSaveOnboardingState)
                }
            }
        }
    }
}