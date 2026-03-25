package com.fadymarty.gametime.presentation.create_account

sealed interface CreateAccountEvent {
    data object OnTogglePasswordVisibility : CreateAccountEvent
    data object OnToggleConfirmPasswordVisibility : CreateAccountEvent
    data object OnCreateAccountClick : CreateAccountEvent
    data object OnLoginClick : CreateAccountEvent
}