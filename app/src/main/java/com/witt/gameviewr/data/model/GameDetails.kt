package com.witt.gameviewr.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetails(
    val gameInfo: GameInfo? = null
)

@Serializable
data class GameInfo(
    @SerialName("gameID")
    val id: String? = null,
    val storeID: String? = null,
    val name: String? = null,
    val steamAppID: String? = null,
    val salePrice: String? = null,
    val retailPrice: String? = null,
    val steamRatingText: String? = null,
    val steamRatingPercent: String? = null,
    val steamRatingCount: String? = null,
    val metacriticScore: String? = null,
    val metacriticLink: String? = null,
    val releaseDate: Long? = null,
    val publisher: String? = null,
    val steamworks: String? = null,
    @SerialName("thumb")
    val imageUrl: String? = null
) {
    val formattedReleaseDate: String
        get() = if (releaseDate == null || releaseDate == 0L) {
            "N/A"
        } else {
            java.time.Instant.ofEpochSecond(releaseDate)
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate()
                .format(java.time.format.DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.MEDIUM))
        }
}
