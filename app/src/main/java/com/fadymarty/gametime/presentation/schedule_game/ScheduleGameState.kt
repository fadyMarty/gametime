package com.fadymarty.gametime.presentation.schedule_game

import androidx.compose.foundation.text.input.TextFieldState
import java.time.LocalDate
import java.time.LocalTime

data class ScheduleGameState(
    val gameName: TextFieldState = TextFieldState(),
    val category: String? = null,
    val isCategorySheetVisible: Boolean = false,
    val winningPrice: TextFieldState = TextFieldState(),
    val fromDate: LocalDate = LocalDate.now(),
    val isFromDatePickerVisible: Boolean = false,
    val fromTime: LocalTime = LocalTime.now(),
    val isFromTimePickerVisible: Boolean = false,
    val toDate: LocalDate = LocalDate.now(),
    val isToDatePickerVisible: Boolean = false,
    val toTime: LocalTime = LocalTime.now(),
    val isToTimePickerVisible: Boolean = false,
    val description: TextFieldState = TextFieldState(),
    val isRemindersEnabled: Boolean = true,
)