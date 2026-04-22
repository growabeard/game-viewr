package com.witt.gameviewr.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SheetState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.witt.gameviewr.R
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.model.GameDetails
import com.witt.gameviewr.data.model.GameInfo
import com.witt.gameviewr.ui.GameListUiState
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

        Spacer(modifier = Modifier.height(16.dp))

        GameList(onGameClick, uiState, listState)
    }

    if (showBottomSheet) {
        GameDetailsBottomSheet(uiState.gameDetail, sheetState, onGameDetailsDismiss)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameDetailsBottomSheet(
    gameDetail: GameDetails,
    sheetState: SheetState,
    onGameDetailsDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onGameDetailsDismiss,
        sheetState = sheetState,
    ) {
        val gameInfo = gameDetail.gameInfo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .heightIn(max = 200.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    ),
                model = gameInfo?.imageUrl,
                contentScale = ContentScale.Inside,
                contentDescription = null
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = gameInfo?.name ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                PricingSection(gameInfo)

                RatingSection(gameInfo)

                DetailRow(
                    label = stringResource(R.string.game_details_release_date),
                    value = gameInfo?.formattedReleaseDate
                )
                DetailRow(
                    label = stringResource(R.string.game_details_publisher),
                    value = gameInfo?.publisher
                )
            }
        }
    }
}

@Composable
private fun PricingSection(gameInfo: GameInfo?) {
    val sale = gameInfo?.salePrice
    val retail = gameInfo?.retailPrice
    if (sale == null || retail == null) return

    val isOnSale = sale != retail

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$$sale",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = if (isOnSale) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        if (isOnSale) {
            Text(
                text = "$$retail",
                style = MaterialTheme.typography.titleMedium.copy(
                    textDecoration = TextDecoration.LineThrough
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Composable
private fun RatingSection(gameInfo: GameInfo?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (gameInfo?.steamRatingPercent != null && gameInfo.steamRatingPercent != "0") {
            RatingBadge(
                label = stringResource(R.string.game_details_rating_label_steam),
                score = "${gameInfo.steamRatingPercent}%",
                subtitle = if (gameInfo.steamRatingText != null && gameInfo.steamRatingCount != "0") {
                    "${gameInfo.steamRatingText} (${gameInfo.steamRatingCount})"
                } else {
                    ""
                },
                modifier = Modifier.weight(1f).fillMaxHeight()
            )
        }
        if (gameInfo?.metacriticScore != null && gameInfo.metacriticScore != "0") {
            RatingBadge(
                label = stringResource(R.string.game_details_rating_label_metacritic),
                score = gameInfo.metacriticScore,
                subtitle = stringResource(R.string.game_details_rating_subtitle_score),
                modifier = Modifier.weight(1f).fillMaxHeight()
            )
        }
    }
}

@Composable
private fun RatingBadge(
    label: String,
    score: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = label, style = MaterialTheme.typography.labelSmall)
            Text(
                text = score,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String?) {
    val isValidValue = !value.isNullOrEmpty() && value != "N/A" && value != "0"

    if (isValidValue) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )
        }
    }
}

@Composable
private fun GameList(
    onGameClick: (String) -> Unit,
    uiState: GameListUiState,
    listState: LazyListState
) {
    if (uiState.error != null) {
        ErrorMessage(
            message = uiState.error,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.listOfGames.isEmpty() && !uiState.isLoading) {
            EmptyState(
                query = uiState.query,
                error = uiState.error,
                hasSearched = uiState.hasSearched,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(
                    count = uiState.listOfGames.size,
                    key = { index -> uiState.listOfGames[index].id }
                ) { index ->
                    GameItem(
                        game = uiState.listOfGames[index],
                        onClick = { onGameClick(uiState.listOfGames[index].cheapestDealId) }
                    )
                    if (index < uiState.listOfGames.size - 1) {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun GameItem(
    game: Game,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(72.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = game.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 120.dp, height = 72.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = game.title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
fun ErrorMessage(message: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun EmptyState(
    query: String,
    error: String?,
    hasSearched: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val title = when {
            !hasSearched -> stringResource(R.string.empty_before_search_title)
            error != null -> stringResource(R.string.empty_error_title)
            else -> stringResource(R.string.empty_no_results_title)
        }

        val description = when {
            !hasSearched -> stringResource(R.string.empty_before_search_description)
            error != null -> stringResource(R.string.empty_error_description)
            else -> stringResource(R.string.empty_no_results_description, query)
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
