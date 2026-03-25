package com.fadymarty.gametime.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Классы навигации
 *
 * Дата создания: 24-03-2026
 * Автор создания: 1
 */
sealed interface Route {

    @Serializable
    data object Splash : Route

    @Serializable
    data object Onboarding : Route

    @Serializable
    data object CreateAccount : Route

    @Serializable
    data object Login : Route

    @Serializable
    data object Landing : Route

    @Serializable
    data object Statistics : Route

    @Serializable
    data object DiscoverCombats : Route

    @Serializable
    data object ScheduleGame : Route

    @Serializable
    data object Chat : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data object CombatInformation : Route

    @Serializable
    data object PlayerInformation : Route

    @Serializable
    data object GameImage : Route

    @Serializable
    data object GameCircle : Route
}