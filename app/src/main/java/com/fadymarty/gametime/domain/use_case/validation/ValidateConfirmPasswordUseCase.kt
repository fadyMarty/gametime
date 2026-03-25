package com.fadymarty.gametime.domain.use_case.validation

class ValidateConfirmPasswordUseCase {
    operator fun invoke(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}