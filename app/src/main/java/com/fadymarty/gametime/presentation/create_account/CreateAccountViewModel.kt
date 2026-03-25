package com.fadymarty.gametime.presentation.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadymarty.gametime.domain.use_case.validation.ValidateConfirmPasswordUseCase
import com.fadymarty.gametime.domain.use_case.validation.ValidateEmailUseCase
import com.fadymarty.gametime.presentation.snackbar.SnackbarController
import com.fadymarty.gametime.presentation.snackbar.SnackbarEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateAccountViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateConfirmPasswordUseCase: ValidateConfirmPasswordUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CreateAccountState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<CreateAccountEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onEvent(event: CreateAccountEvent) {
        when (event) {
            CreateAccountEvent.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible
                    )
                }
            }
            CreateAccountEvent.OnToggleConfirmPasswordVisibility -> {
                _state.update {
                    it.copy(
                        isConfirmPasswordVisible = !it.isConfirmPasswordVisible
                    )
                }
            }
            CreateAccountEvent.OnCreateAccountClick -> {
                createAccount()
            }
            else -> Unit
        }
    }

    private fun createAccount() {
        val isEmailValid = validateEmailUseCase(_state.value.emailState.text.toString())
        val isConfirmPasswordValid = validateConfirmPasswordUseCase(
            password = _state.value.passwordState.text.toString(),
            confirmPassword = _state.value.confirmPasswordState.text.toString()
        )
        if (!isEmailValid) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent("Введите корректный email"))
            }
            return
        }
        if (!isConfirmPasswordValid) {
            viewModelScope.launch {
                SnackbarController.sendEvent(SnackbarEvent("Пароли не совпадают"))
            }
            return
        }
        viewModelScope.launch {
            eventChannel.send(CreateAccountEvent.OnCreateAccountClick)
        }
    }
}