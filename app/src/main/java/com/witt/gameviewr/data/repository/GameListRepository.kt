package com.witt.gameviewr.data.repository

import com.witt.gameviewr.data.service.GameApiService
import com.witt.gameviewr.data.model.Game
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameListRepository @Inject constructor(
    private val apiService: GameApiService
) {
    suspend fun getGames(title: String): List<Game> {
        return apiService.getGames(title)
    }

    suspend fun getDeals(): List<Game> {
        return apiService.getDeals()
    }
}