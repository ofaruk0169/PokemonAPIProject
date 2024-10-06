package com.example.projectpokemon.data.remote

import android.util.Log
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
            // Calculate offset based on load type
            val offset = when (loadType) {
                LoadType.REFRESH -> 0 // Start from the beginning for a refresh
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        // If no last item, start from the beginning
                        0
                    } else {
                        // Calculate the offset from the last item's ID
                        lastItem.id
                    }
                }
            }


            //Make the API call with offset and limit
            delay(2000L)
            val pokemonsListResponse = pokemonApi.getPokemons(
                offset = offset,
                limit = state.config.pageSize
            ).results


            // Fetch detailed data for each Pokémon using the URL from the list response
            val detailedPokemonList = pokemonsListResponse.map { basicPokemonDto ->
                val pokemonDetailDto = pokemonApi.getPokemonDetails(basicPokemonDto.url)
                pokemonDetailDto
            }

            // Log the Pokémon DTOs to see if any fields are null
            detailedPokemonList.forEach { pokemonDetailDto ->
                Log.d(
                    "PokemonDetailDto",
                    "Name: ${pokemonDetailDto.name}}"
                )
            }


            pokemonDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDb.dao.clearAll()
                }
                val pokemonEntities = detailedPokemonList.map { it.toPokemonEntity() }
                pokemonDb.dao.upsertAll(pokemonEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = pokemonsListResponse.isEmpty()
            )

        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: retrofit2.HttpException) {
            MediatorResult.Error(e)
        }
    }
}


