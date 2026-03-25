package com.fadymarty.gametime.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.gametime.domain.use_case.settings.ReadOnboardingStateUseCase
import com.fadymarty.gametime.presentation.navigation.Route
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val readOnboardingStateUseCase: ReadOnboardingStateUseCase,
) : ViewModel() {

    private val eventChannel = Channel<SplashEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            readOnboardingStateUseCase().collect { completed ->
                if (completed) {
                    eventChannel.send(SplashEvent.OnNavigate(Route.Landing))
                } else {
                    eventChannel.send(SplashEvent.OnNavigate(Route.Onboarding))
                }
            }
        }
    }
}