package com.witt.gameviewr.data

import com.witt.gameviewr.data.model.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {
    @GET("games")
    suspend fun getGames(@Query("title") title: String): List<Game>
}