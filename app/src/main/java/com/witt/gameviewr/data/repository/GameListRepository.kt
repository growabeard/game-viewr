package com.witt.gameviewr.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.model.GameResponse
import com.witt.gameviewr.data.paging.GamesPagingSource
import com.witt.gameviewr.data.service.GameApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameListRepository @Inject constructor(
    private val apiService: GameApiService
) {
    fun getDealsStream(query: String): Flow<PagingData<Game>> {
        val seenIds = mutableSetOf<String>()
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                enablePlaceholders = false,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED
            ),
            pagingSourceFactory = { GamesPagingSource(this, query) }
        ).flow.map { pagingData ->
            pagingData.filter { game ->
                seenIds.add("${game.id}-${game.dealID}")
            }
        }
    }

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
    ): GameResponse {
        return try {
            val games = apiService.getAllDeals(
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
            GameResponse.Success(games)
        } catch (e: Exception) {
            GameResponse.Error(e.message ?: "Unknown error occurred")
        }
    }
}