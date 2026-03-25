package com.fadymarty.gametime.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.fadymarty.gametime.di.dataStore
import com.fadymarty.gametime.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Репозиторий для управления настройками приложения
 *
 * Дата создания: 24-03-2026
 * Автор создания: 1
 *
 * @param context контекст
 */
class SettingsRepositoryImpl(
    private val context: Context,
) : SettingsRepository {

    /**
     * Функция для сохранения состояния экранов приветствия
     *
     * @param completed просмотрены ли экраны приветствия или нет
     */
    override suspend fun saveOnboardingState(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = completed
        }
    }

    /**
     * Функция для чтения состояния экранов приветствия
     */
    override fun readOnboardingState(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[ONBOARDING_COMPLETED] ?: false
        }
    }

    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }
}