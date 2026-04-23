package com.witt.gameviewr.data.repository

import com.witt.gameviewr.data.model.Deal
import com.witt.gameviewr.data.model.GameDetails
import com.witt.gameviewr.data.service.GameApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameListRepository @Inject constructor(
    private val apiService: GameApiService
) {
    suspend fun getDeals(
        pageNumber: Int? = null,
        pageSize: Int = 25,
        title: String,
        sortBy: String? = null,
        desc: Boolean? = null,
        lowerPrice: Int? = null,
        upperPrice: Int? = null,
        metacritic: Int? = null,
        steamRating: Int? = null,
        minimumReviewCount: Int? = null,
        maxAge: Int? = null,
        steamAppID: String? = null,
        aaa: Boolean? = null,
        steamworks: Boolean? = null,
        onSale: Boolean? = null,
        output: String? = null
    ): List<Deal> {
        return apiService.getAllDeals(
            pageNumber = pageNumber,
            pageSize = pageSize,
            sortBy = sortBy,
            desc = desc,
            lowerPrice = lowerPrice,
            upperPrice = upperPrice,
            metacritic = metacritic,
            steamRating = steamRating,
            minimumReviewCount = minimumReviewCount,
            maxAge = maxAge,
            steamAppID = steamAppID,
            title = title,
            exact = true,
            AAA = aaa,
            steamworks = steamworks,
            onSale = onSale,
            output = output
        )
    }

    suspend fun getGameDetails(id: String): GameDetails {
        return apiService.getGameDetails(id)
    }
}