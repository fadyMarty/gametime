package com.fadymarty.gametime.presentation.schedule_game.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fadymarty.uikit.buttons.PrimaryButton
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Компонент выбора даты
 *
 * Дата создания: 25-03-2026
 * Автор создания: 1
 */
@Composable
fun ScheduleGameDatePicker(
    state: DatePickerState,
    onDismiss: () -> Unit,
    onSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            PrimaryButton(
                modifier = Modifier.padding(start = 6.dp),
                label = "Confirm",
                onClick = {
                    state.selectedDateMillis?.let { millis ->
                        val date = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        onSelected(date)
                    }
                }
            )
        }
    ) {
        DatePicker(state)
    }
}