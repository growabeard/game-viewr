package com.witt.gameviewr.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: String,
    val title: String
)