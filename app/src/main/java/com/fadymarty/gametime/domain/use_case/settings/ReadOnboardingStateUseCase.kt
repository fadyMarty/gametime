package com.fadymarty.gametime.domain.use_case.settings

import com.fadymarty.gametime.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingStateUseCase(
    private val settingsRepository: SettingsRepository,
) {
    operator fun invoke(): Flow<Boolean> {
        return settingsRepository.readOnboardingState()
    }
}