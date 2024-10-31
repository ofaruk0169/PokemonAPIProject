package com.example.projectpokemon.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.example.projectpokemon.data.local.PokemonDatabase
import com.example.projectpokemon.data.local.PokemonEntity
import com.example.projectpokemon.data.mappers.toPokemonEntity
import kotlinx.coroutines.delay
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator (
    private val pokemonDb: PokemonDatabase,
    private val pokemonApi: PokemonApi
): RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {

        return try {
            val offset = when(loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                } //calculate offset based on current state
            }

            //fetch date from the API
            val response = pokemonApi.getPokemons(
                offset,
                state.config.pageSize
            )

            val pokemonEntities = response.results.map { result ->
                val id = result.url.substringAfterLast("/").toInt()
                PokemonDto(
                    id = id,
                    name = result.name,
                    types = "", // Placeholder or fetch types if needed
                    abilities = "", // Placeholder or fetch abilities if needed
                    height = "", // Placeholder or fetch height if needed
                    spriteUrl = null,
                    url = result.url// Placeholder or fetch spriteUrl if needed
                )
            }.map { dto ->
                dto.toPokemonEntity() // Use the mapping function here
            }


            pokemonDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    pokemonDb.dao.clearAll()
                }
                pokemonDb.dao.upsertAll(pokemonEntities)
            }

            /*
            MediatorResult.Success(
                endOfPaginationReached = pokemons.isEmpty()
            )*/

            MediatorResult.Success(endOfPaginationReached = response.next == null)


        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: retrofit2.HttpException) {
            MediatorResult.Error(e)
        }
    }
}




