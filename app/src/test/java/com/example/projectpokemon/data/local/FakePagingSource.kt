package com.example.projectpokemon.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState

class FakePagingSource(
    private val data: List<PokemonEntity>
) : PagingSource<Int, PokemonEntity>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPokemon = state.closestItemToPosition(anchorPosition)
            anchorPokemon?.id
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return LoadResult.Page(
            data = data,
            prevKey = null,  // Since we are mocking, assume no previous key
            nextKey = null   // Assume no next key
        )
    }
}