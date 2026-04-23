package com.witt.gameviewr.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.witt.gameviewr.data.model.Game
import com.witt.gameviewr.data.model.GameResponse
import com.witt.gameviewr.data.repository.GameListRepository

class GamesPagingSource(
    private val repository: GameListRepository,
    private val query: String
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        return try {
            val page = params.key ?: 0
            val response = repository.getDeals(
                title = query,
                pageNumber = page,
                pageSize = params.loadSize
            )

            when (response) {
                is GameResponse.Success -> {
                    LoadResult.Page(
                        data = response.games,
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (response.games.isEmpty()) null else page + 1
                    )
                }

                is GameResponse.Error -> {
                    LoadResult.Error(Exception(response.message))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}