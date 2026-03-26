package com.fadymarty.gametime.presentation.discover_combats

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DiscoverCombatsViewModel : ViewModel() {

    private val _state = MutableStateFlow(DiscoverCombatsState())
    val state = _state.asStateFlow()
}