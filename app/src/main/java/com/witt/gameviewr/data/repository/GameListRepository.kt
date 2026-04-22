package com.witt.gameviewr.data.repository

import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.model.GameDetails
import com.witt.gameviewr.data.service.GameApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameListRepository @Inject constructor(
    private val apiService: GameApiService
) {
    suspend fun getGames(title: String): List<Game> {
        return apiService.getGames(title)
    }

    suspend fun getGameDetails(id: String): GameDetails {
        return apiService.getDeals(id)
    }
}