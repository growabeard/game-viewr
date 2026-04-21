package com.witt.gameviewr.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.repository.GameListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(private val repository: GameListRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(GameListUiState())
    val uiState = _uiState.asStateFlow()

    fun onSearch(query: String) { //TODO implement this
        val trimmedQuery = query.trim()
        if (trimmedQuery.isEmpty()) return

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null, hasSearched = true) }

                delay(2000) // Simulate network call

                _uiState.update {
                    it.copy(
                        listOfGames = (1..(Math.random() * 100).toInt()).map { index ->
                            Game(
                                id = index.toString(),
                                title = "Game $index"
                            )
                        },
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "An unknown error occurred",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onClearInputClick() {
        onQueryChange("")
    }

    fun onQueryChange(newQuery: String) {
        _uiState.value = _uiState.value.copy(query = newQuery)
    }
}

data class GameListUiState(
    val query: String = "",
    val listOfGames: List<Game> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasSearched: Boolean = false
)