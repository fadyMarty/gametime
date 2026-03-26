package com.fadymarty.gametime.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import coil3.ImageLoader
import com.fadymarty.gametime.common.util.Constants
import com.fadymarty.gametime.data.repository.GameImageRepositoryImpl
import com.fadymarty.gametime.data.repository.SettingsRepositoryImpl
import com.fadymarty.gametime.domain.repository.GameRepository
import com.fadymarty.gametime.domain.repository.SettingsRepository
import com.fadymarty.gametime.domain.use_case.game.GetImagePiecesUseCase
import com.fadymarty.gametime.domain.use_case.settings.ReadOnboardingStateUseCase
import com.fadymarty.gametime.domain.use_case.settings.SaveOnboardingStateUseCase
import com.fadymarty.gametime.domain.use_case.validation.ValidateConfirmPasswordUseCase
import com.fadymarty.gametime.domain.use_case.validation.ValidateEmailUseCase
import com.fadymarty.gametime.presentation.create_account.CreateAccountViewModel
import com.fadymarty.gametime.presentation.discover_combats.DiscoverCombatsViewModel
import com.fadymarty.gametime.presentation.game_circle.GameCircleViewModel
import com.fadymarty.gametime.presentation.game_image.GameImageViewModel
import com.fadymarty.gametime.presentation.login.LoginViewModel
import com.fadymarty.gametime.presentation.onboarding.OnboardingViewModel
import com.fadymarty.gametime.presentation.player_information.PlayerInformationViewModel
import com.fadymarty.gametime.presentation.schedule_game.ScheduleGameViewModel
import com.fadymarty.gametime.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore(Constants.DATASTORE_NAME)

/**
 * DI-модуль приложения
 *
 * Дата создания: 23-03-2026
 * Автор создания: 1
 */
val appModule = module {

    single {
        ImageLoader.Builder(androidContext()).build()
    }

    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
    singleOf(::GameImageRepositoryImpl) { bind<GameRepository>() }

    factoryOf(::SaveOnboardingStateUseCase)
    factoryOf(::ReadOnboardingStateUseCase)

    factoryOf(::ValidateEmailUseCase)
    factoryOf(::ValidateConfirmPasswordUseCase)

    factoryOf(::GetImagePiecesUseCase)

    viewModelOf(::SplashViewModel)
    viewModelOf(::OnboardingViewModel)
    viewModelOf(::CreateAccountViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::DiscoverCombatsViewModel)
    viewModelOf(::ScheduleGameViewModel)
    viewModelOf(::PlayerInformationViewModel)
    viewModelOf(::GameImageViewModel)
    viewModelOf(::GameCircleViewModel)
}