package com.witt.gameviewr.ui

import android.util.Log
import androidx.paging.PagingData
import com.witt.gameviewr.MainDispatcherRule
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.repository.GameListRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
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
    fun `ensure onSearch with empty query will not update hasSearched state`() {
        viewModel.onSearch("")

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        assert(!viewModel.uiState.value.hasSearched)
    }

    @Test
    fun `ensure onSearch will update hasSearched state`() {
        viewModel.onSearch("some query")

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.uiState.value.hasSearched)
    }

    @Test
    fun `ensure onGameClick will update gameDetail state`() {
        val game = Game(
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

        viewModel.onGameClick(game)

        assertEquals(game, viewModel.uiState.value.gameDetail)
    }

    @Test
    fun `ensure onQueryChange will update the query in state`() {
        assertEquals("", viewModel.uiState.value.query)

        viewModel.onQueryChange("some query")

        assertEquals("some query", viewModel.uiState.value.query)
    }

    @Test
    fun `ensure onClearInputClick will clear the query in state`() {
        assertEquals("", viewModel.uiState.value.query)

        viewModel.onQueryChange("some query")

        assertEquals("some query", viewModel.uiState.value.query)

        viewModel.onClearInputClick()

        assertEquals("", viewModel.uiState.value.query)
    }

    @Test
    fun `ensure onGameDetailsDismiss will clear gameDetail state`() {
        val game = Game(
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

        viewModel.onGameClick(game)
        assertEquals(game, viewModel.uiState.value.gameDetail)

        viewModel.onGameDetailsDismiss()
        assertEquals(null, viewModel.uiState.value.gameDetail)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `ensure onSearch triggers repository getDealsStream`() = runTest {
        val query = "Batman"
        every { repository.getDealsStream(any()) } returns flowOf(PagingData.empty())

        // Start collecting the flow to make it "active"
        val job = launch {
            viewModel.gamesFlow.collect {}
        }

        viewModel.onSearch(query)

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        verify { repository.getDealsStream(query) }
        assert(viewModel.uiState.value.hasSearched)

        job.cancel()
    }
}