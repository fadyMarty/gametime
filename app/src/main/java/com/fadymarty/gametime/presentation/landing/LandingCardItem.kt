package com.fadymarty.gametime.presentation.landing

import androidx.annotation.DrawableRes
import com.fadymarty.gametime.R
import com.fadymarty.gametime.presentation.navigation.Route

sealed class LandingCardItem(
    val title: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val route: Route,
) {
    data object Schedule : LandingCardItem(
        title = "Schedule",
        description = "Easily schedule event/games\nthen find like minded players for battle. You up for it?",
        image = R.drawable.img_schedule_card,
        route = Route.ScheduleGame
    )

    data object Statistics : LandingCardItem(
        title = "Statistics",
        description = "All data from previous and \nupcoming games can be found here ",
        image = R.drawable.img_statistics_card,
        route = Route.Statistics
    )

    data object DiscoverCombats : LandingCardItem(
        title = "Discover  Combats",
        description = "Find out what’s new and compete among players with new challenges and earn cash with game points ",
        image = R.drawable.img_discover_combats_card,
        route = Route.DiscoverCombats
    )

    data object MessagePlayers : LandingCardItem(
        title = "Message Players",
        description = "Found the profile of a player\nthat interests you? Start a\nconversation",
        image = R.drawable.img_message_players_card,
        route = Route.Chat
    )
}