package com.witt.gameviewr

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameListViewModel : ViewModel() {

    var query by mutableStateOf("")
        private set

    var listOfGames by mutableStateOf(listOf<String>())
        private set

    fun onSearch(query: String) { //TODO implement this
        viewModelScope.launch {
            listOfGames = emptyList()
            delay(700)
            listOfGames = (1..(Math.random() * 100).toInt()).map { "Game $it" }
        }
    }

    fun onQueryChange(newQuery: String) {
        query = newQuery
    }
}