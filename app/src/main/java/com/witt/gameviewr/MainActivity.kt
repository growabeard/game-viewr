package com.witt.gameviewr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.witt.gameviewr.ui.GameListViewModel
import com.witt.gameviewr.ui.screens.GameListScreen
import com.witt.gameviewr.ui.theme.GameViewrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<GameListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GameViewrTheme {
                var currentDestination by remember { mutableStateOf(HomeDestination.Deals) }

                NavigationSuiteScaffold(
                    navigationSuiteItems = {
                        item(
                            selected = currentDestination == HomeDestination.Deals,
                            onClick = { currentDestination = HomeDestination.Deals },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = null
                                )
                            },
                            label = { Text(stringResource(R.string.deals_tab_title)) }
                        )
                        item(
                            selected = currentDestination == HomeDestination.Search,
                            onClick = { currentDestination = HomeDestination.Search },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            label = { Text(stringResource(R.string.search_tab_title)) }
                        )
                    }
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        when (currentDestination) {
                            HomeDestination.Search -> {
                                GameListScreen(
                                    uiState = viewModel.uiState.collectAsStateWithLifecycle(),
                                    onSearch = viewModel::onSearch,
                                    onQueryChange = viewModel::onQueryChange,
                                    onClearInputClick = viewModel::onClearInputClick,
                                    onGameDetailsDismiss = viewModel::onGameDetailsDismiss,
                                    onGameClick = viewModel::onGameClick,
                                    modifier = Modifier.padding(innerPadding),
                                )
                            }

                            HomeDestination.Deals -> { //TODO remove this
                                Text("We probably don't need this anymore..")
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class HomeDestination {
    Search, Deals
}