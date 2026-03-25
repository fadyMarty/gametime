package com.fadymarty.gametime.presentation.create_account

import androidx.compose.foundation.text.input.TextFieldState

data class CreateAccountState(
    val fullNameState: TextFieldState = TextFieldState(),
    val userNameState: TextFieldState = TextFieldState(),
    val countryCodeState: TextFieldState = TextFieldState(),
    val phoneNumberState: TextFieldState = TextFieldState(),
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val confirmPasswordState: TextFieldState = TextFieldState(),
    val isConfirmPasswordVisible: Boolean = false,
)
