package com.example.jetendemicsid.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetendemicsid.data.HewanRepository
import com.example.jetendemicsid.model.Hewan
import com.example.jetendemicsid.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HewanRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Hewan>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Hewan>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllHewan() {
        viewModelScope.launch {
            repository.getAllHewan()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { listHewan ->
                    _uiState.value = UiState.Success(listHewan)
                }
        }
    }

    fun search(newQuery: String) {
        _query.value = newQuery
        _uiState.value = UiState.Success(repository.searchHewan(_query.value))
    }
}