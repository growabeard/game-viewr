package com.witt.gameviewr.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.witt.gameviewr.data.model.Deal
import com.witt.gameviewr.data.model.GameDetails
import com.witt.gameviewr.data.repository.GameListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(private val repository: GameListRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(GameListUiState())
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val dealsFlow: Flow<PagingData<Deal>> = _searchQuery
        .flatMapLatest { query ->
            repository.getDealsStream(query)
        }
        .cachedIn(viewModelScope)

    fun onSearch(query: String) {
        Log.d(TAG, "onSearch: $query")
        val trimmedQuery = query.trim()
        if (trimmedQuery.isEmpty()) return

        _uiState.update { it.copy(hasSearched = true) }
        _searchQuery.value = trimmedQuery
    }

    fun onGameClick(dealID: String) {
        Log.d(TAG, "onGameClick: $dealID")
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null, hasSearched = true) }

                val result = repository.getGameDetails(dealID)

                Log.d(TAG, "onGameClick: $result")

                _uiState.update {
                    it.copy(
                        gameDetail = result,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "onGameClick: ${e.message}", e)
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
        Log.d(TAG, "onClearInputClick")
        onQueryChange("")
    }

    fun onGameDetailsDismiss() {
        Log.d(TAG, "onGameDetailsDismiss")
        _uiState.update { it.copy(gameDetail = null) }
    }

    fun onQueryChange(newQuery: String) {
        Log.d(TAG, "onQueryChange: $newQuery")
        _uiState.value = _uiState.value.copy(query = newQuery)
    }

    companion object {
        private const val TAG = "GameListViewModel"
    }
}

data class GameListUiState(
    val query: String = "",
    val gameDetail: GameDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasSearched: Boolean = false
)