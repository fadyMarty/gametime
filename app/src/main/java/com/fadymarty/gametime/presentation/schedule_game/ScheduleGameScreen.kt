package com.fadymarty.gametime.presentation.schedule_game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fadymarty.gametime.R
import com.fadymarty.gametime.presentation.schedule_game.components.ScheduleGameDatePicker
import com.fadymarty.gametime.presentation.schedule_game.components.ScheduleGameTimePicker
import com.fadymarty.uikit.buttons.PrimaryButton
import com.fadymarty.uikit.checkbox.Checkbox
import com.fadymarty.uikit.common.theme.GameTimeTheme
import com.fadymarty.uikit.inputs.Input
import com.fadymarty.uikit.select.Select
import org.koin.compose.viewmodel.koinViewModel
import java.time.format.DateTimeFormatter

/**
 * Экран Schedule Game
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun ScheduleGameRoot(
    onBackClick: () -> Unit,
    viewModel: ScheduleGameViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScheduleGameScreen(
        state = state,
        onEvent = { event ->
            when (event) {
                ScheduleGameEvent.OnBackClick -> {
                    onBackClick()
                }
                else -> Unit
            }
            viewModel.onEvent(event)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScheduleGameScreen(
    state: ScheduleGameState,
    onEvent: (ScheduleGameEvent) -> Unit,
) {
    val fromDatePickerState = rememberDatePickerState(
        initialSelectedDate = state.fromDate
    )
    val fromTimePickerState = rememberTimePickerState(
        initialHour = state.fromTime.hour,
        initialMinute = state.fromTime.minute
    )
    val toDatePickerState = rememberDatePickerState(
        initialSelectedDate = state.toDate
    )
    val toTimePickerState = rememberTimePickerState(
        initialHour = state.toTime.hour,
        initialMinute = state.toTime.minute
    )

    val dateFormatter = remember {
        DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")
    }
    val timeFormatter = remember {
        DateTimeFormatter.ofPattern("hh:mm a")
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp)
                .padding(top = 16.dp, bottom = 25.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(20.dp, 14.2.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = false)
                    ) {
                        onEvent(ScheduleGameEvent.OnBackClick)
                    },
                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                contentDescription = null,
                tint = GameTimeTheme.colorScheme.accentInactive
            )
            Spacer(modifier = Modifier.height(35.8.dp))
            Text(
                text = "Schedule Game",
                style = GameTimeTheme.typography.title1Extrabold,
                color = GameTimeTheme.colorScheme.accentInactive
            )
            Spacer(modifier = Modifier.height(30.dp))
            Input(
                state = state.gameName,
                placeholder = "Game Name"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) {
                            onEvent(ScheduleGameEvent.OnChangeCategorySheetVisibility(true))
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (state.category != null) {
                            Text(
                                text = state.category,
                                style = GameTimeTheme.typography.caption2Regular
                            )
                        } else {
                            Text(
                                text = "Category",
                                style = GameTimeTheme.typography.caption2Regular
                            )
                        }
                    }
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(8.dp, 4.dp),
                        painter = painterResource(R.drawable.ic_dropdown),
                        contentDescription = null,
                        tint = GameTimeTheme.colorScheme.accentInactive
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = GameTimeTheme.colorScheme.accent
                            )
                        )
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Input(
                state = state.winningPrice,
                placeholder = "Winning Price"
            )
            Spacer(modifier = Modifier.height(47.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Select(
                    value = dateFormatter.format(state.fromDate),
                    onClick = {
                        onEvent(ScheduleGameEvent.OnChangeFromDatePickerVisibility(true))
                    },
                    label = "FROM"
                )
                Select(
                    value = timeFormatter.format(state.fromTime),
                    onClick = {
                        onEvent(ScheduleGameEvent.OnChangeFromTimePickerVisibility(true))
                    }
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Select(
                    value = dateFormatter.format(state.toDate),
                    onClick = {
                        onEvent(ScheduleGameEvent.OnChangeToDatePickerVisibility(true))
                    },
                    label = "TO"
                )
                Select(
                    value = timeFormatter.format(state.toTime),
                    onClick = {
                        onEvent(ScheduleGameEvent.OnChangeToTimePickerVisibility(true))
                    }
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Input(
                state = state.description,
                placeholder = "Description",
                singleLine = false
            )
            Spacer(modifier = Modifier.height(55.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "REMINDERS",
                    style = GameTimeTheme.typography.caption2Regular,
                    color = GameTimeTheme.colorScheme.accentInactive
                )
                Row(
                    modifier = Modifier
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) {
                            onEvent(ScheduleGameEvent.OnToggleRemindersEnabled)
                        },
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = state.isRemindersEnabled
                    ) {
                        onEvent(ScheduleGameEvent.OnToggleRemindersEnabled)
                    }
                    Text(
                        text = "Notification",
                        style = GameTimeTheme.typography.caption2Regular
                    )
                }
            }
            Spacer(modifier = Modifier.height(44.dp))
            PrimaryButton(
                modifier = Modifier.padding(horizontal = 51.dp),
                label = "Publish"
            ) {
                onEvent(ScheduleGameEvent.OnPublishClick)
            }
        }
    }

    if (state.isFromDatePickerVisible) {
        ScheduleGameDatePicker(
            state = fromDatePickerState,
            onDismiss = {
                onEvent(ScheduleGameEvent.OnChangeFromDatePickerVisibility(false))
            },
            onSelected = { date ->
                onEvent(ScheduleGameEvent.OnFromDateSelected(date))
            }
        )
    }
    if (state.isFromTimePickerVisible) {
        ScheduleGameTimePicker(
            state = fromTimePickerState,
            onDismiss = {
                onEvent(ScheduleGameEvent.OnChangeFromTimePickerVisibility(false))
            },
            onSelected = { time ->
                onEvent(ScheduleGameEvent.OnFromTimeSelected(time))
            }
        )
    }
    if (state.isToDatePickerVisible) {
        ScheduleGameDatePicker(
            state = toDatePickerState,
            onDismiss = {
                onEvent(ScheduleGameEvent.OnChangeToDatePickerVisibility(false))
            },
            onSelected = { date ->
                onEvent(ScheduleGameEvent.OnToDateSelected(date))
            }
        )
    }
    if (state.isToTimePickerVisible) {
        ScheduleGameTimePicker(
            state = toTimePickerState,
            onDismiss = {
                onEvent(ScheduleGameEvent.OnChangeToTimePickerVisibility(false))
            },
            onSelected = { time ->
                onEvent(ScheduleGameEvent.OnToTimeSelected(time))
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScheduleGameScreenPreview() {
    GameTimeTheme {
        ScheduleGameScreen(
            state = ScheduleGameState(),
            onEvent = {}
        )
    }
}