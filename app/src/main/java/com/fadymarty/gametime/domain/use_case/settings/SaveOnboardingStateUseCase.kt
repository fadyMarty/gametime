package com.fadymarty.gametime.domain.use_case.settings

import com.fadymarty.gametime.domain.repository.SettingsRepository

class SaveOnboardingStateUseCase(
    private val settingsRepository: SettingsRepository,
) {
    suspend operator fun invoke(completed: Boolean) {
        settingsRepository.saveOnboardingState(completed)
    }
}