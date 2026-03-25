package com.fadymarty.gametime.presentation.landing

import com.fadymarty.gametime.presentation.navigation.Route

sealed interface LandingEvent {
    data class OnNavigate(val route: Route) : LandingEvent
}