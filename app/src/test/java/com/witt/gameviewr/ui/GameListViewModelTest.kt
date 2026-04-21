package com.witt.gameviewr.ui

import com.witt.gameviewr.MainDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: GameListViewModel

    @Before
    fun setUp() {
        viewModel = GameListViewModel()
    }

    @Test
    fun `ensure onSearch will update the game list in state`() {
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
}