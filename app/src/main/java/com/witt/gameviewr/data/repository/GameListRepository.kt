package com.witt.gameviewr.data.repository

import com.witt.gameviewr.data.GameApiService
import com.witt.gameviewr.data.model.Game

class GameListRepository(
    private val apiService: GameApiService
) {
    suspend fun getGames(title: String): List<Game> {
        return apiService.getGames(title)
    }
}