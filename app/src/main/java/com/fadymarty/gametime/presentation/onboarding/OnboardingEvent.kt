package com.fadymarty.gametime.presentation.onboarding

sealed interface OnboardingEvent {
    data object OnSaveOnboardingState : OnboardingEvent
}