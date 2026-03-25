package com.fadymarty.gametime.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

data class LoginState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
)