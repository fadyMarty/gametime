package com.fadymarty.gametime.presentation.schedule_game

import java.time.LocalDate
import java.time.LocalTime

sealed interface ScheduleGameEvent {
    data object OnBackClick : ScheduleGameEvent
    data class OnChangeCategorySheetVisibility(val isVisible: Boolean) : ScheduleGameEvent
    data class OnCategorySelected(val category: String) : ScheduleGameEvent
    data class OnChangeFromDatePickerVisibility(val isVisible: Boolean) : ScheduleGameEvent
    data class OnFromDateSelected(val fromDate: LocalDate) : ScheduleGameEvent
    data class OnChangeFromTimePickerVisibility(val isVisible: Boolean) : ScheduleGameEvent
    data class OnFromTimeSelected(val fromTime: LocalTime) : ScheduleGameEvent
    data class OnChangeToDatePickerVisibility(val isVisible: Boolean) : ScheduleGameEvent
    data class OnToDateSelected(val toDate: LocalDate) : ScheduleGameEvent
    data class OnChangeToTimePickerVisibility(val isVisible: Boolean) : ScheduleGameEvent
    data class OnToTimeSelected(val toTime: LocalTime) : ScheduleGameEvent
    data object OnToggleRemindersEnabled : ScheduleGameEvent
    data object OnPublishClick : ScheduleGameEvent
}