package com.witt.gameviewr.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    @SerialName("gameID")
    val id: String,
    @SerialName("external")
    val title: String,
    @SerialName("cheapest")
    val price: String?,
    @SerialName("thumb")
    val imageUrl: String,
    @SerialName("steamAppID")
    val steamAppId: String?,
    @SerialName("cheapestDealID")
    val cheapestDealId: String,
)