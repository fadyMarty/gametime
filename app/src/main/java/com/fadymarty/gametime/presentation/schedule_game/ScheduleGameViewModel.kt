package com.fadymarty.gametime.presentation.schedule_game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleGameViewModel : ViewModel() {

    private val _state = MutableStateFlow(ScheduleGameState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<ScheduleGameEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onEvent(event: ScheduleGameEvent) {
        when (event) {
            is ScheduleGameEvent.OnCategorySelected -> {
                _state.update {
                    it.copy(
                        category = event.category
                    )
                }
            }
            is ScheduleGameEvent.OnChangeCategorySheetVisibility -> {
                _state.update {
                    it.copy(
                        isCategorySheetVisible = event.isVisible
                    )
                }
            }
            is ScheduleGameEvent.OnFromDateSelected -> {
                _state.update {
                    it.copy(
                        fromDate = event.fromDate,
                        isFromDatePickerVisible = false
                    )
                }
            }
            is ScheduleGameEvent.OnChangeFromDatePickerVisibility -> {
                _state.update {
                    it.copy(
                        isFromDatePickerVisible = event.isVisible
                    )
                }
            }
            is ScheduleGameEvent.OnFromTimeSelected -> {
                _state.update {
                    it.copy(
                        fromTime = event.fromTime,
                        isFromTimePickerVisible = false
                    )
                }
            }
            is ScheduleGameEvent.OnChangeFromTimePickerVisibility -> {
                _state.update {
                    it.copy(
                        isFromTimePickerVisible = event.isVisible
                    )
                }
            }
            is ScheduleGameEvent.OnToDateSelected -> {
                _state.update {
                    it.copy(
                        toDate = event.toDate,
                        isToDatePickerVisible = false
                    )
                }
            }
            is ScheduleGameEvent.OnChangeToDatePickerVisibility -> {
                _state.update {
                    it.copy(
                        isToDatePickerVisible = event.isVisible
                    )
                }
            }
            is ScheduleGameEvent.OnToTimeSelected -> {
                _state.update {
                    it.copy(
                        toTime = event.toTime,
                        isToTimePickerVisible = false
                    )
                }
            }
            is ScheduleGameEvent.OnChangeToTimePickerVisibility -> {
                _state.update {
                    it.copy(
                        isToTimePickerVisible = event.isVisible
                    )
                }
            }
            ScheduleGameEvent.OnToggleRemindersEnabled -> {
                _state.update {
                    it.copy(
                        isRemindersEnabled = !it.isRemindersEnabled
                    )
                }
            }
            ScheduleGameEvent.OnPublishClick -> {
                viewModelScope.launch {
                    eventChannel.send(ScheduleGameEvent.OnPublishClick)
                }
            }
            else -> Unit
        }
    }
}