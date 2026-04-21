package com.witt.gameviewr.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.witt.gameviewr.ui.GameListUiState
import com.witt.gameviewr.R
import com.witt.gameviewr.data.Game
import com.witt.gameviewr.ui.theme.GameViewrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
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
    modifier: Modifier = Modifier
) {
    val uiState = uiState.value

    Column(modifier = modifier) {
        TextField(
            value = uiState.query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text(stringResource(R.string.search_bar_placeholder)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (uiState.query.isNotEmpty()) {
                    IconButton(onClick = { onQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.clear_search_query)
                        )
                    }
                }
            },
            shape = SearchBarDefaults.inputFieldShape,
            colors = SearchBarDefaults.inputFieldColors(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch(uiState.query) })
        )

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
