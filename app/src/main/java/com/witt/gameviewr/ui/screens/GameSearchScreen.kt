package com.witt.gameviewr.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.witt.gameviewr.R
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.model.GameDetails
import com.witt.gameviewr.data.model.GameInfo
import com.witt.gameviewr.ui.GameListUiState
import com.witt.gameviewr.ui.components.GameDetailsBottomSheet
import com.witt.gameviewr.ui.components.GameList
import com.witt.gameviewr.ui.theme.GameViewrTheme

@Preview(showSystemUi = true)
@Composable
fun GameListScreenPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = listOf(
                            Game(
                                id = "1",
                                title = "Game 1",
                                price = "1.03",
                                imageUrl = "https://shared.fastly.steamstatic.com/store_item_assets/steam/app/1404210/capsule_231x87.jpg?t=1759502979",
                                steamAppId = "123456",
                                cheapestDealId = "123456"
                            ),
                            Game(
                                id = "2",
                                title = "Game 2",
                                price = "1.03",
                                imageUrl = "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/541720/capsule_231x87.jpg?t=1690508086",
                                steamAppId = "123456",
                                cheapestDealId = "123456"
                            ),
                            Game(
                                id = "3",
                                title = "Game 3",
                                price = "1.03",
                                imageUrl = "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2668510/c9c0dd50da5b3543c74beb11b6c806fb9fd88a8c/capsule_231x87.jpg?t=1741118459",
                                steamAppId = "123456",
                                cheapestDealId = "123456"
                            ),
                        ),
                        query = "Search Query",
                        isLoading = false,
                        error = null
                    )
                )
            },
            onSearch = {},
            onQueryChange = {},
            onClearInputClick = {},
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListScreenDetailsPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = listOf(
                            Game(
                                id = "1",
                                title = "Game 1",
                                price = "1.03",
                                imageUrl = "https://shared.fastly.steamstatic.com/store_item_assets/steam/app/1404210/capsule_231x87.jpg?t=1759502979",
                                steamAppId = "123456",
                                cheapestDealId = "123456"
                            )
                        ),
                        gameDetail = GameDetails(
                            gameInfo = GameInfo(
                                name = "Game 1",
                                salePrice = "1.03",
                                steamRatingText = "Very Positive",
                                id = "1",
                                storeID = "1234",
                                steamAppID = "12345",
                                retailPrice = "15.90",
                                steamRatingPercent = "80",
                                steamRatingCount = "6476",
                                metacriticScore = "80",
                                metacriticLink = null,
                                releaseDate = 1234567890,
                                publisher = "My publisher",
                                steamworks = "1",
                                imageUrl = "https://cdn.fanatical.com/production/product/400x225/105f34ca-7757-47ad-953e-7df7f016741e.jpeg",
                            )
                        ),
                        query = "Search Query",
                        isLoading = false,
                        error = null
                    )
                )
            },
            onSearch = {},
            onQueryChange = {},
            onClearInputClick = {},
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListScreenInitialPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = emptyList(),
                        query = "",
                        isLoading = false,
                        hasSearched = false,
                        error = null
                    )
                )
            },
            onSearch = {},
            onQueryChange = {},
            onClearInputClick = {},
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListScreenLoadingPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = emptyList(),
                        query = "Loading query",
                        isLoading = true,
                        error = null
                    )
                )
            },
            onSearch = {},
            onQueryChange = {},
            onClearInputClick = {},
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListScreenErrorPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = emptyList(),
                        query = "Loading query",
                        isLoading = false,
                        error = "There was an error"
                    )
                )
            },
            onSearch = {},
            onQueryChange = {},
            onClearInputClick = {},
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListScreenErrorEmptyPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = emptyList(),
                        query = "",
                        isLoading = false,
                        error = "There was an error"
                    )
                )
            },
            onSearch = {},
            onQueryChange = {},
            onClearInputClick = {},
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListScreenErrorLoadingPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = emptyList(),
                        query = "",
                        isLoading = true,
                        error = "There was an error"
                    )
                )
            },
            onSearch = {},
            onQueryChange = {},
            onClearInputClick = {},
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    uiState: State<GameListUiState>,
    onSearch: (String) -> Unit,
    onQueryChange: (String) -> Unit,
    onClearInputClick: () -> Unit,
    onGameDetailsDismiss: () -> Unit,
    onGameClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = uiState.value
    val listState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val showBottomSheet = uiState.gameDetail != null

    LaunchedEffect(uiState.listOfGames) {
        if (uiState.listOfGames.isNotEmpty()) {
            listState.scrollToItem(0)
        }
    }

    Column(modifier = modifier) {

        SearchBar(uiState, onQueryChange, onClearInputClick, onSearch)

        Spacer(modifier = Modifier.height(16.dp))

        GameList(onGameClick, uiState, listState)
    }

    if (showBottomSheet) {
        GameDetailsBottomSheet(uiState.gameDetail, sheetState, onGameDetailsDismiss)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SearchBar(
    uiState: GameListUiState,
    onQueryChange: (String) -> Unit,
    onClearInputClick: () -> Unit,
    onSearch: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .clip(SearchBarDefaults.inputFieldShape)
    ) {
        TextField(
            enabled = !uiState.isLoading,
            value = uiState.query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(R.string.search_bar_placeholder)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (uiState.query.isNotEmpty()) {
                    IconButton(onClick = onClearInputClick) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.clear_search_query)
                        )
                    }
                }
            },
            colors = SearchBarDefaults.inputFieldColors().copy(
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch(uiState.query) }),
            isError = uiState.error != null
        )

        if (uiState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(3.dp)
            )
        }
    }
}
