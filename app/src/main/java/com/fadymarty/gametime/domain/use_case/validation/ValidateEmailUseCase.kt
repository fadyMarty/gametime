package com.fadymarty.gametime.domain.use_case.validation

class ValidateEmailUseCase {

    private val emailRegex = Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]+$")

    operator fun invoke(email: String): Boolean {
        return emailRegex.matches(email)
    }
}