package com.fadymarty.gametime.presentation.discover_combats

import com.fadymarty.gametime.presentation.navigation.Route

sealed interface DiscoverCombatsEvent {
    data object OnBackClick : DiscoverCombatsEvent
    data class OnNavigate(val route: Route) : DiscoverCombatsEvent
    data class OnPlayerClick(val id: String) : DiscoverCombatsEvent
}