package com.witt.gameviewr.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.witt.gameviewr.data.Game
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameListUiState())
    val uiState = _uiState.asStateFlow()

    fun onSearch(query: String) { //TODO implement this
        try {
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        listOfGames = emptyList()
                    )
                }
                delay(700)
                //TODO move this to the repository when implemented
                _uiState.update {
                    it.copy(
                        listOfGames = (1..(Math.random() * 100).toInt()).map { index ->
                            Game(
                                id = index.toString(),
                                title = "Game $index"
                            )
                        },
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        } finally {
            _uiState.update {
                it.copy(
                    isLoading = false
                )
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
    val error: String? = null
)