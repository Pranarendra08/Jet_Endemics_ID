package com.example.jetendemicsid.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetendemicsid.data.HewanRepository
import com.example.jetendemicsid.model.Hewan
import com.example.jetendemicsid.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: HewanRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Hewan>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Hewan>>
        get() = _uiState

    fun getHewanById(hewanId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getHewanById(hewanId))
        }
    }
}