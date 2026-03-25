package com.fadymarty.gametime.presentation.schedule_game.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fadymarty.uikit.buttons.PrimaryButton
import java.time.LocalTime

/**
 * Компонент выбора времени
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleGameTimePicker(
    state: TimePickerState,
    onDismiss: () -> Unit,
    onSelected: (LocalTime) -> Unit,
    modifier: Modifier = Modifier,
) {
    TimePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            PrimaryButton(
                label = "Confirm",
                onClick = {
                    val time = LocalTime.of(state.hour, state.minute)
                    onSelected(time)
                }
            )
        },
        dismissButton = {},
        title = {
            Text("Select time")
        }
    ) {
        TimePicker(state)
    }
}