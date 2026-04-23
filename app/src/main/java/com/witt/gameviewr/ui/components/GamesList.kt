package com.witt.gameviewr.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.witt.gameviewr.R
import com.witt.gameviewr.data.model.Deal
import com.witt.gameviewr.ui.GameListUiState

@Composable
fun GameList(
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
                    key = { index -> "${uiState.listOfGames[index].id}-${uiState.listOfGames[index].title}-$index" } //Try removing this and searching for "ste" and scrolling down..
                ) { index ->
                    GameItem(
                        game = uiState.listOfGames[index],
                        onClick = { onGameClick(uiState.listOfGames[index].dealID) }
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
    game: Deal,
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