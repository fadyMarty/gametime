package com.fadymarty.gametime.presentation.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fadymarty.gametime.R
import com.fadymarty.gametime.presentation.navigation.Route
import com.fadymarty.uikit.bottom_bar.BottomBar
import com.fadymarty.uikit.bottom_bar.BottomBarScreen
import com.fadymarty.uikit.card.LandingCard
import com.fadymarty.uikit.common.theme.GameTimeTheme
import kotlinx.coroutines.launch

/**
 * Экран Landing
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun LandingRoot(
    onNavigate: (Route) -> Unit,
) {
    LandingScreen(
        onEvent = { event ->
            when (event) {
                is LandingEvent.OnNavigate -> {
                    onNavigate(event.route)
                }
            }
        }
    )
}

@Composable
private fun LandingScreen(
    onEvent: (LandingEvent) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val cardItems = remember {
        listOf(
            LandingCardItem.Schedule,
            LandingCardItem.Statistics,
            LandingCardItem.DiscoverCombats,
            LandingCardItem.MessagePlayers
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxSize(),
                drawerState = drawerState
            ) {

            }
        }
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets.statusBars
                .union(WindowInsets.displayCutout)
        ) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 13.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 27.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(17.5.dp, 10.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = ripple(bounded = false, radius = 17.5.dp)
                                ) {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                            imageVector = ImageVector.vectorResource(R.drawable.ic_hamburger),
                            contentDescription = null,
                            tint = GameTimeTheme.colorScheme.accentInactive
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .size(22.dp)
                                    .border(
                                        width = 1.dp,
                                        brush = Brush.linearGradient(GameTimeTheme.colorScheme.accent),
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape),
                                painter = painterResource(R.drawable.img_profile_pic),
                                contentDescription = null
                            )
                            Text(
                                text = "Stone Stellar",
                                style = GameTimeTheme.typography.caption2Regular
                            )
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(
                            start = 30.dp,
                            top = 35.dp,
                            end = 30.dp,
                            bottom = 115.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(cardItems) { item ->
                            LandingCard(
                                title = item.title,
                                description = item.description,
                                image = painterResource(item.image),
                                onClick = {
                                    onEvent(LandingEvent.OnNavigate(item.route))
                                }
                            )
                        }
                    }
                }
                BottomBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onItemClick = { item ->
                        when (item) {
                            BottomBarScreen.Statistics -> {
                                onEvent(LandingEvent.OnNavigate(Route.Statistics))
                            }
                            BottomBarScreen.DiscoverCombats -> {
                                onEvent(LandingEvent.OnNavigate(Route.DiscoverCombats))
                            }
                            BottomBarScreen.ScheduleGame -> {
                                onEvent(LandingEvent.OnNavigate(Route.ScheduleGame))
                            }
                            BottomBarScreen.Chat -> {
                                onEvent(LandingEvent.OnNavigate(Route.Chat))
                            }
                            BottomBarScreen.Profile -> {
                                onEvent(LandingEvent.OnNavigate(Route.Profile))
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LandingScreenPreview() {
    GameTimeTheme {
        LandingScreen(
            onEvent = {}
        )
    }
}