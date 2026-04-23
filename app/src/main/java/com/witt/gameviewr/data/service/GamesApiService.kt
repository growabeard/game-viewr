package com.witt.gameviewr.data.service

import com.witt.gameviewr.data.model.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {
    @GET("deals")
    suspend fun getAllDeals(
        @Query("storeID") storeID: String? = null,
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("desc") desc: Boolean? = null,
        @Query("lowerPrice") lowerPrice: Int? = null,
        @Query("upperPrice") upperPrice: Int? = null,
        @Query("metacritic") metacritic: Int? = null,
        @Query("steamRating") steamRating: Int? = null,
        @Query("minimumReviewCount") minimumReviewCount: Int? = null,
        @Query("maxAge") maxAge: Int? = null,
        @Query("steamAppID") steamAppID: String? = null,
        @Query("title") title: String? = null,
        @Query("exact") exact: Boolean? = null,
        @Query("AAA") AAA: Boolean? = null,
        @Query("steamworks") steamworks: Boolean? = null,
        @Query("onSale") onSale: Boolean? = null,
        @Query("output") output: String? = null
    ): List<Game>
}