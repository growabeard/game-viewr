package com.witt.gameviewr.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Deal(
    val internalName: String,
    val title: String,
    val metacriticLink: String? = null,
    val dealID: String,
    val storeID: String,
    @SerialName("gameID")
    val id: String,
    val salePrice: String,
    @SerialName("normalPrice")
    val retailPrice: String,
    val isOnSale: String,
    val savings: String,
    val metacriticScore: String? = null,
    val steamRatingText: String? = null,
    val steamRatingPercent: String? = null,
    val steamRatingCount: String? = null,
    val steamAppID: String? = null,
    val releaseDate: Long,
    val lastChange: Long,
    val dealRating: String,
    @SerialName("thumb")
    val imageUrl: String
)
