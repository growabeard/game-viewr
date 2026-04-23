package com.witt.gameviewr.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.witt.gameviewr.R
import com.witt.gameviewr.data.model.Deal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsBottomSheet(
    gameInfo: Deal,
    sheetState: SheetState,
    onGameDetailsDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onGameDetailsDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .heightIn(max = 300.dp, min = 100.dp),
                model = gameInfo.imageUrl,
                contentScale = ContentScale.FillHeight,
                contentDescription = null
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = gameInfo.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                PricingSection(gameInfo)

                RatingSection(gameInfo)

                DetailRow(
                    label = stringResource(R.string.game_details_release_date),
                    value = gameInfo.formattedReleaseDate
                )
            }
        }
    }
}

@Composable
private fun PricingSection(gameInfo: Deal) {
    val sale = gameInfo.salePrice
    val retail = gameInfo.retailPrice

    val isOnSale = sale != retail

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
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
private fun RatingSection(gameInfo: Deal) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (gameInfo.steamRatingPercent != null && gameInfo.steamRatingPercent != "0") {
            RatingBadge(
                label = stringResource(R.string.game_details_rating_label_steam),
                score = "${gameInfo.steamRatingPercent}%",
                subtitle = if (gameInfo.steamRatingText != null && gameInfo.steamRatingCount != "0") {
                    "${gameInfo.steamRatingText} (${gameInfo.steamRatingCount})"
                } else {
                    ""
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
        if (gameInfo.metacriticScore != null && gameInfo.metacriticScore != "0") {
            RatingBadge(
                label = stringResource(R.string.game_details_rating_label_metacritic),
                score = gameInfo.metacriticScore,
                subtitle = stringResource(R.string.game_details_rating_subtitle_score),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
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