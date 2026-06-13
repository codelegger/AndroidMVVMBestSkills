package com.codelegger.androidmvvmbestskills.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelegger.androidmvvmbestskills.domain.model.Sample
import com.codelegger.androidmvvmbestskills.domain.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/** UI state for the Home screen. */
data class HomeUiState(
    val isLoading: Boolean = false,
    val samples: List<Sample> = emptyList(),
    val errorMessage: String? = null,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SampleRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeSamples()
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            runCatching { repository.refresh() }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message)
                }
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
        }
    }

    private fun observeSamples() {
        repository.observeSamples()
            .onEach { samples -> _uiState.value = _uiState.value.copy(samples = samples) }
            .catch { e -> _uiState.value = _uiState.value.copy(errorMessage = e.message) }
            .launchIn(viewModelScope)
    }
}
