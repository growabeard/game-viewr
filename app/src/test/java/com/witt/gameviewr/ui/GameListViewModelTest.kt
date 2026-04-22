package com.witt.gameviewr.ui

import android.util.Log
import com.witt.gameviewr.MainDispatcherRule
import com.witt.gameviewr.data.model.Game
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
        coEvery { repository.getGames(any()) } returns emptyList()

        viewModel.onSearch("some query")

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.uiState.value.listOfGames.isEmpty())
        assert(viewModel.uiState.value.hasSearched)
    }

    @Test
    fun `ensure onSearch will update the game list in state`() {
        coEvery { repository.getGames(any()) } returns (1..(Math.random() * 100).toInt()).map { index ->
            Game(
                id = index.toString(),
                title = "Game $index",
                price = "1.03",
                imageUrl = "https://www.example.com/$index/img.png",
                steamAppId = "123456",
                cheapestDealId = "123456"
            )
        }

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