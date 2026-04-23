package com.witt.gameviewr.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.witt.gameviewr.data.model.SearchGame
import com.witt.gameviewr.data.model.GameDetails
import com.witt.gameviewr.data.model.GameInfo
import com.witt.gameviewr.ui.GameListUiState
import com.witt.gameviewr.ui.components.GameDetailsBottomSheet
import com.witt.gameviewr.ui.components.GameList
import com.witt.gameviewr.ui.theme.GameViewrTheme

@Preview(showSystemUi = true)
@Composable
fun GameDealsScreenPreview() {
    GameViewrTheme {
        GameDealsScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = listOf(
                            SearchGame(
                                id = "1",
                                title = "Game 1",
                                price = "1.03",
                                imageUrl = "https://shared.fastly.steamstatic.com/store_item_assets/steam/app/1404210/capsule_231x87.jpg?t=1759502979",
                                steamAppId = "123456",
                                cheapestDealId = "123456"
                            ),
                            SearchGame(
                                id = "2",
                                title = "Game 2",
                                price = "1.03",
                                imageUrl = "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/541720/capsule_231x87.jpg?t=1690508086",
                                steamAppId = "123456",
                                cheapestDealId = "123456"
                            ),
                            SearchGame(
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
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameDealsScreenDetailsPreview() {
    GameViewrTheme {
        GameDealsScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = listOf(
                            SearchGame(
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
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameDealsScreenInitialPreview() {
    GameViewrTheme {
        GameDealsScreen(
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
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameDealsScreenLoadingPreview() {
    GameViewrTheme {
        GameDealsScreen(
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
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameDealsScreenErrorPreview() {
    GameViewrTheme {
        GameDealsScreen(
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
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameDealsScreenErrorEmptyPreview() {
    GameViewrTheme {
        GameDealsScreen(
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
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameDealsScreenErrorLoadingPreview() {
    GameViewrTheme {
        GameDealsScreen(
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
            onGameDetailsDismiss = {},
            onGameClick = {},
            modifier = Modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDealsScreen(
    uiState: State<GameListUiState>,
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
        GameList(onGameClick, uiState, listState)
    }

    if (showBottomSheet) {
        GameDetailsBottomSheet(uiState.gameDetail, sheetState, onGameDetailsDismiss)
    }
}