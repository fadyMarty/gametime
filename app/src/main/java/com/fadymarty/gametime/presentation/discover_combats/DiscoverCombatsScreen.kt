package com.fadymarty.gametime.presentation.discover_combats

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fadymarty.gametime.R
import com.fadymarty.gametime.presentation.discover_combats.components.CombatCategoryTitle
import com.fadymarty.gametime.presentation.discover_combats.components.CombatItem
import com.fadymarty.gametime.presentation.discover_combats.components.PlayerItem
import com.fadymarty.gametime.presentation.discover_combats.components.SearchInput
import com.fadymarty.gametime.presentation.navigation.Route
import com.fadymarty.uikit.bottom_bar.BottomBar
import com.fadymarty.uikit.bottom_bar.BottomBarScreen
import com.fadymarty.uikit.common.theme.GameTimeTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DiscoverCombatsRoot(
    onBackClick: () -> Unit,
    onNavigate: (Route) -> Unit,
    onPlayerClick: (String) -> Unit,
    viewModel: DiscoverCombatsViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DiscoverCombatsScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                DiscoverCombatsEvent.OnBackClick -> {
                    onBackClick()
                }
                is DiscoverCombatsEvent.OnNavigate -> {
                    onNavigate(event.route)
                }
                is DiscoverCombatsEvent.OnPlayerClick -> {
                    onPlayerClick(event.id)
                }
            }
        }
    )
}

@Composable
private fun DiscoverCombatsScreen(
    state: DiscoverCombatsState,
    onEvent: (DiscoverCombatsEvent) -> Unit,
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 109.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    colors = GameTimeTheme.colorScheme.accent
                                )
                            )
                            .padding(
                                top = innerPadding.calculateTopPadding()
                            )
                            .padding(horizontal = 30.dp)
                            .padding(top = 16.dp, bottom = 29.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp, 14.2.dp)
                                .clickable(
                                    interactionSource = null,
                                    indication = ripple(bounded = false)
                                ) {
                                    onEvent(DiscoverCombatsEvent.OnBackClick)
                                },
                            imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                            contentDescription = null,
                            tint = GameTimeTheme.colorScheme.onAccent
                        )
                        Spacer(modifier = Modifier.height(35.8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Discover \nCombats",
                                style = GameTimeTheme.typography.title1Extrabold,
                                color = GameTimeTheme.colorScheme.onAccent
                            )
                            Text(
                                modifier = Modifier.padding(bottom = 4.dp),
                                text = "FILTER",
                                style = GameTimeTheme.typography.caption2Bold,
                                color = GameTimeTheme.colorScheme.onAccent
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        SearchInput(
                            state = state.searchQueryState,
                            placeholder = "Search Combat"
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
                item {
                    CombatCategoryTitle(
                        title = "Trending this week",
                        onViewAllClick = {}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(4) { index ->
                    CombatItem(
                        onClick = {
                            onEvent(
                                DiscoverCombatsEvent.OnNavigate(
                                    route = if (index % 2 == 0) {
                                        Route.GameImage
                                    } else Route.GameCircle
                                )
                            )
                        }
                    )
                    Spacer(
                        modifier = Modifier.height(
                            height = if (index == 3) 41.dp else 10.dp
                        )
                    )
                }
                item {
                    CombatCategoryTitle(
                        title = "Most Popular Players",
                        onViewAllClick = {}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        PlayerItem(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onEvent(DiscoverCombatsEvent.OnPlayerClick("other"))
                            }
                        )
                        PlayerItem(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onEvent(DiscoverCombatsEvent.OnPlayerClick("other"))
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(34.dp))
                }
                item {
                    CombatCategoryTitle(
                        title = "Latest Combats",
                        onViewAllClick = {}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                items(4) { index ->
                    CombatItem(
                        onClick = {
                            onEvent(
                                DiscoverCombatsEvent.OnNavigate(
                                    route = if (index % 2 == 0) {
                                        Route.GameImage
                                    } else Route.GameCircle
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            BottomBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                selectedScreen = BottomBarScreen.DiscoverCombats,
                onScreenClick = { screen ->
                    when (screen) {
                        BottomBarScreen.Statistics -> {
                            onEvent(DiscoverCombatsEvent.OnNavigate(Route.Statistics))
                        }
                        BottomBarScreen.ScheduleGame -> {
                            onEvent(DiscoverCombatsEvent.OnNavigate(Route.ScheduleGame))
                        }
                        BottomBarScreen.Chat -> {
                            onEvent(DiscoverCombatsEvent.OnNavigate(Route.Chat))
                        }
                        BottomBarScreen.Profile -> {
                            onEvent(
                                DiscoverCombatsEvent.OnNavigate(
                                    Route.PlayerInformation("current")
                                )
                            )
                        }
                        else -> Unit
                    }
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DiscoverCombatsPreview() {
    GameTimeTheme {
        DiscoverCombatsScreen(
            state = DiscoverCombatsState(),
            onEvent = {}
        )
    }
}