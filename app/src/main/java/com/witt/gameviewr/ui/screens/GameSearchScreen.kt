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
import com.witt.gameviewr.data.model.Deal
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
