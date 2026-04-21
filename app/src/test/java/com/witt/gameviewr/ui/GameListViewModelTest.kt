package com.witt.gameviewr.ui

import com.witt.gameviewr.MainDispatcherRule
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.repository.GameListRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
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

        viewModel = GameListViewModel(repository)
    }

    @Test
    fun `ensure onSearch with empty query will not update the game list`() {
        viewModel.onSearch("")

        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        assert(viewModel.uiState.value.listOfGames.isEmpty())
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

    @Test
    fun `ensure onClearInputClick will clear the query in state`() {
        viewModel.onQueryChange("some query")

        assertEquals("some query", viewModel.uiState.value.query)

        viewModel.onClearInputClick()

        assertEquals("", viewModel.uiState.value.query)
    }
}