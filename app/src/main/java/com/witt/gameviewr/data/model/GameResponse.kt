package com.witt.gameviewr.data.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface GameResponse {
    @Serializable
    data class Success(val games: List<Game>) : GameResponse

    @Serializable
    data class Error(val message: String) : GameResponse
}
