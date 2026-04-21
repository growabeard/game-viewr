package com.witt.gameviewr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.witt.gameviewr.ui.screens.GameListScreen
import com.witt.gameviewr.ui.theme.GameViewrTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<GameListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GameViewrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameListScreen(
                        query = viewModel.query,
                        onSearch = viewModel::onSearch,
                        onQueryChange = viewModel::onQueryChange,
                        modifier = Modifier.padding(innerPadding),
                        searchResults = viewModel.listOfGames
                    )
                }
            }
        }
    }
}
