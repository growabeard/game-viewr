package com.witt.gameviewr.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import com.witt.gameviewr.ui.GameListUiState
import com.witt.gameviewr.R
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.ui.theme.GameViewrTheme

@Preview(showSystemUi = true)
@Composable
fun GameListPreview() {
    GameViewrTheme {
        GameListScreen(
            uiState = remember {
                mutableStateOf(
                    GameListUiState(
                        listOfGames = listOf(
                            Game(id = "1", title = "Game 1"),
                            Game(id = "2", title = "Game 2"),
                            Game(id = "3", title = "Game 3"),
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
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListInitialPreview() {
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
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListLoadingPreview() {
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
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListErrorPreview() {
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
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListErrorEmptyPreview() {
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
            modifier = Modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GameListErrorLoadingPreview() {
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
    modifier: Modifier = Modifier
) {
    val uiState = uiState.value

    Column(modifier = modifier) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .clip(SearchBarDefaults.inputFieldShape)
        ) {
            TextField(
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

        Spacer(modifier = Modifier.height(8.dp))

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
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(
                    count = uiState.listOfGames.size,
                    key = { index -> uiState.listOfGames[index].id }
                ) { index ->
                    Text(
                        text = uiState.listOfGames[index].title,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
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
            !hasSearched -> "Search for Games"
            error != null -> "Something went wrong"
            else -> "No games found"
        }

        val description = when {
            !hasSearched -> "Enter a game title above to see detailed information."
            error != null -> "Check your connection and try again."
            else -> "We couldn't find anything for \"$query\""
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
