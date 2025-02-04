package com.example.projectpokemon.data.local

import androidx.paging.PagingSource

class FakePokemonDao(
    private val fakeDatabase: MutableList<PokemonEntity>
) : PokemonDao {

    override suspend fun upsertAll(pokemons: List<PokemonEntity>) {
        fakeDatabase.addAll(pokemons)
    }

    // This simulates the paging source
    override fun pagingSource(): PagingSource<Int, PokemonEntity> {
        return FakePagingSource(fakeDatabase)
    }

    override suspend fun clearAll() {
        fakeDatabase.clear()
    }
}