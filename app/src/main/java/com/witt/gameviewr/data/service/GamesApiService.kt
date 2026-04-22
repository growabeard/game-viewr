package com.witt.gameviewr.data.service

import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.model.GameDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {
    @GET("games")
    suspend fun getGames(@Query("title") title: String): List<Game>

    @GET("deals")
    suspend fun getDeals(@Query("id", encoded = true) id: String): GameDetails
}