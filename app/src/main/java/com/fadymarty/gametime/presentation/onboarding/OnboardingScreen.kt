package com.fadymarty.gametime.presentation.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadymarty.gametime.common.util.ObserveAsEvents
import com.fadymarty.gametime.presentation.onboarding.components.OnboardingPagerScreen
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.common.theme.GameTimeTheme
import com.fadymarty.uikit.indicator.Pagination
import org.koin.compose.viewmodel.koinViewModel

/**
 * Приветственные экраны
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun OnboardingRoot(
    onSaveOnboardingState: () -> Unit,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            OnboardingEvent.OnSaveOnboardingState -> {
                onSaveOnboardingState()
            }
        }
    }

    OnboardingScreen(
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun OnboardingScreen(
    onEvent: (OnboardingEvent) -> Unit,
) {
    val pagerState = rememberPagerState { 3 }

    val pages = remember {
        listOf(
            OnboardingPage.First,
            OnboardingPage.Second,
            OnboardingPage.Third
        )
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState
            ) { index ->
                OnboardingPagerScreen(
                    page = pages[index]
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 75.dp)
                    .padding(bottom = 9.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (pagerState.currentPage == 2) {
                    PrimaryButton(
                        label = "Let’s Combat!",
                        onClick = {
                            onEvent(OnboardingEvent.OnSaveOnboardingState)
                        }
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = null,
                                indication = null
                            ) {
                                onEvent(OnboardingEvent.OnSaveOnboardingState)
                            },
                        text = "Skip",
                        style = GameTimeTheme.typography.captionRegular,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                        color = GameTimeTheme.colorScheme.accentInactive
                    )
                }
                Spacer(
                    modifier = Modifier.height(
                        height = if (pagerState.currentPage == 2) 38.dp else 71.dp
                    )
                )
                Pagination(
                    pageCount = pagerState.pageCount,
                    currentPage = pagerState.currentPage
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun OnboardingScreenPreview() {
    GameTimeTheme {
        OnboardingScreen(
            onEvent = {}
        )
    }
}