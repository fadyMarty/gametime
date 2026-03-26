package com.fadymarty.gametime.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fadymarty.gametime.common.util.ObserveAsEvents
import com.fadymarty.gametime.presentation.create_account.CreateAccountRoot
import com.fadymarty.gametime.presentation.discover_combats.DiscoverCombatsRoot
import com.fadymarty.gametime.presentation.game_circle.GameCircleRoot
import com.fadymarty.gametime.presentation.game_image.GameImageRoot
import com.fadymarty.gametime.presentation.landing.LandingRoot
import com.fadymarty.gametime.presentation.login.LoginRoot
import com.fadymarty.gametime.presentation.onboarding.OnboardingRoot
import com.fadymarty.gametime.presentation.player_information.PlayerInformationRoot
import com.fadymarty.gametime.presentation.schedule_game.ScheduleGameRoot
import com.fadymarty.gametime.presentation.snackbar.SnackbarController
import com.fadymarty.gametime.presentation.splash.SplashRoot
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Основной граф навигации
 *
 * Дата создания: 24-03-2026
 * Автор создания: 1
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationRoot() {
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    ObserveAsEvents(SnackbarController.events) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val job = launch {
                snackbarHostState.showSnackbar(
                    message = event.message,
                    withDismissAction = true,
                    duration = SnackbarDuration.Indefinite
                )
            }
            delay(5000L)
            job.cancel()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Route.Splash
        ) {
            composable<Route.Splash> {
                SplashRoot(
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(Route.Splash) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Route.Onboarding> {
                OnboardingRoot(
                    onSaveOnboardingState = {
                        navController.navigate(Route.CreateAccount) {
                            popUpTo(Route.Onboarding) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Route.CreateAccount> {
                CreateAccountRoot(
                    onCreateAccountClick = {
                        navController.navigate(Route.Landing) {
                            popUpTo(Route.CreateAccount) {
                                inclusive = true
                            }
                        }
                    },
                    onLoginClick = {
                        navController.navigate(Route.Login) {
                            popUpTo<Route.CreateAccount> {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Route.Login> {
                LoginRoot(
                    onLoginClick = {
                        navController.navigate(Route.Landing) {
                            popUpTo(Route.Login) {
                                inclusive = true
                            }
                        }
                    },
                    onCreateAccountClick = {
                        navController.navigate(Route.CreateAccount) {
                            popUpTo<Route.Login> {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Route.Landing> {
                LandingRoot(
                    onNavigate = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable<Route.Statistics> {

            }
            composable<Route.DiscoverCombats> {
                DiscoverCombatsRoot(
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onPlayerClick = { id ->
                        navController.navigate(Route.PlayerInformation(id))
                    },
                    onNavigate = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable<Route.ScheduleGame> {
                ScheduleGameRoot(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
            composable<Route.Chat> {

            }
            composable<Route.PlayerInformation> {
                val information = it.toRoute<Route.PlayerInformation>()
                PlayerInformationRoot(
                    id = information.id,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
            composable<Route.GameImage> {
                GameImageRoot()
            }
            composable<Route.GameCircle> {
                GameCircleRoot()
            }
        }
    }
}