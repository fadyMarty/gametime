package com.fadymarty.gametime.presentation.login

sealed interface LoginEvent {
    data object OnTogglePasswordVisibility : LoginEvent
    data object OnLoginClick : LoginEvent
    data object OnCreateAccountClick : LoginEvent
}