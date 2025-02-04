package com.example.projectpokemon.data.local
import androidx.paging.PageKeyedDataSource

class FakeDatabaseRepository/*(
    private val fakePokemonDao: FakePokemonDao
) {
    suspend fun getAllPokemons(): List<PokemonEntity> {
        return fakePokemonDao.pagingSource().load(PageKeyedDataSource.LoadParams.Refresh(1, 20, false)).data
    }

    suspend fun clearDatabase() {
        fakePokemonDao.clearAll()
    }

    suspend fun insertPokemons(pokemons: List<PokemonEntity>) {
        fakePokemonDao.upsertAll(pokemons)
    }
}*/
