package com.witt.gameviewr.ui

import android.util.Log
import com.witt.gameviewr.MainDispatcherRule
import com.witt.gameviewr.data.model.Deal
import com.witt.gameviewr.data.repository.GameListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: GameListViewModel

    @RelaxedMockK
    private lateinit var repository: GameListRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockkStatic(Log::class)

        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0

        viewModel = GameListViewModel(repository)
    }

    @Test
    fun `ensure onSearch with empty query will not update the game list`() {
        viewModel.onSearch("")

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.uiState.value.listOfGames.isEmpty())
    }

    @Test
    fun `ensure onSearch with empty response will not update the state`() {
        coEvery { repository.getDeals(title = any()) } returns emptyList()

        viewModel.onSearch("some query")

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.uiState.value.listOfGames.isEmpty())
        assert(viewModel.uiState.value.hasSearched)
    }

    @Test
    fun `ensure onSearch will update the game list in state`() {
        coEvery { repository.getDeals(title = any()) } returns
                listOf(
                    Deal(
                        internalName = "DEUSEXHUMANREVOLUTIONDIRECTORSCUT",
                        title = "Deus Ex: Human Revolution - Director's Cut",
                        metacriticLink = "/game/pc/deus-ex-human-revolution---directors-cut",
                        dealID = "HhzMJAgQYGZ%2B%2BFPpBG%2BRFcuUQZJO3KXvlnyYYGwGUfU%3D",
                        storeID = "1",
                        id = "102249",
                        salePrice = "2.99",
                        retailPrice = "19.99",
                        isOnSale = "1",
                        savings = "85.042521",
                        metacriticScore = "91",
                        steamRatingText = "Very Positive",
                        steamRatingPercent = "92",
                        steamRatingCount = "17993",
                        steamAppID = "238010",
                        releaseDate = 1382400000,
                        lastChange = 1621536418,
                        dealRating = "9.6",
                        imageUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/238010/capsule_sm_120.jpg?t=1619788192"
                    ),
                    Deal(
                        internalName = "THIEFDEADLYSHADOWS",
                        title = "Thief: Deadly Shadows",
                        metacriticLink = "/game/pc/thief-deadly-shadows",
                        dealID = "EX0oH20b7A1H2YiVjvVx5A0HH%2F4etw3x%2F6YMGVPpKbA%3D",
                        storeID = "1",
                        id = "396",
                        salePrice = "0.98",
                        retailPrice = "8.99",
                        isOnSale = "1",
                        savings = "89.098999",
                        metacriticScore = "85",
                        steamRatingText = "Very Positive",
                        steamRatingPercent = "81",
                        steamRatingCount = "1670",
                        steamAppID = "6980",
                        releaseDate = 1085443200,
                        lastChange = 1621540561,
                        dealRating = "9.4",
                        imageUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/6980/capsule_sm_120.jpg?t=1592493801"
                    ),
                    Deal(
                        internalName = "JUSTCAUSE2",
                        title = "Just Cause 2",
                        metacriticLink = "/game/pc/just-cause-2",
                        dealID = "z4El8C19yCEHrk1%2ByEedebThQVbblI7H0Z%2BAmxgZiS8%3D",
                        storeID = "1",
                        id = "180",
                        salePrice = "1.49",
                        retailPrice = "14.99",
                        isOnSale = "1",
                        savings = "90.060040",
                        metacriticScore = "84",
                        steamRatingText = "Very Positive",
                        steamRatingPercent = "90",
                        steamRatingCount = "35296",
                        steamAppID = "8190",
                        releaseDate = 1269302400,
                        lastChange = 1621536477,
                        dealRating = "9.4",
                        imageUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/8190/capsule_sm_120.jpg?t=1593180404"
                    )
                )

        viewModel.onSearch("some query")

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.uiState.value.listOfGames.isNotEmpty())
    }

    @Test
    fun `ensure onQueryChange will update the query in state`() {
        assertEquals("", viewModel.uiState.value.query)

        viewModel.onQueryChange("some query")

        assertEquals("some query", viewModel.uiState.value.query)
    }

    @Test
    fun `ensure onClearInputClick will clear the query in state`() {
        viewModel.onQueryChange("some query")

        assertEquals("some query", viewModel.uiState.value.query)

        viewModel.onClearInputClick()

        assertEquals("", viewModel.uiState.value.query)
    }
}