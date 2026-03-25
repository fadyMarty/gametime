package com.fadymarty.gametime.presentation.onboarding

import androidx.annotation.DrawableRes
import com.fadymarty.gametime.R

/**
 * Классы с данными для привественных экранов
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {
    data object First : OnboardingPage(
        image = R.drawable.img_onboarding_first,
        title = "Get Paid! Playing Video Game",
        description = "Earn points and real cash when you win a battle with no delay in cashing out"
    )

    data object Second : OnboardingPage(
        image = R.drawable.img_onboarding_second,
        title = "Schedule Games With Friends ",
        description = "Easily create an upcoming event and get ready for battle. Yeah! real combat fella."
    )

    data object Third : OnboardingPage(
        image = R.drawable.img_onboarding_third,
        title = "Text, Audio and Video Chat",
        description = "Intuitive real life experience on mobile. Chat with fellow gamers before and after combat for free!"
    )
}