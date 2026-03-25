package com.fadymarty.gametime.presentation.splash

import com.fadymarty.gametime.presentation.navigation.Route

sealed interface SplashEvent {
    data class OnNavigate(val route: Route) : SplashEvent
}