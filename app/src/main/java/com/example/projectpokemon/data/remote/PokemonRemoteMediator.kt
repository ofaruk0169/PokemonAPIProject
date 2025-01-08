package com.example.projectpokemon.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.projectpokemon.data.local.PokemonDatabase
import com.example.projectpokemon.data.local.PokemonEntity
import com.example.projectpokemon.data.mappers.toPokemonEntity
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
                    lastItem?.id ?: 1
                }
            }

            // Fetch data from the API
            val response = pokemonApi.getPokemons(offset, state.config.pageSize)

            val pokemonEntities = response.results.mapNotNull { result ->
                // Fetch Pokémon ID from URL
                val url = result.url
                Log.d("Omare", "Processing Pokémon URL: $url")

                // Check if URL is not blank and contains the expected segment "/pokemon/"
                if (url.isNotBlank() && url.contains("/pokemon/")) {
                    val idString = result.url.trimEnd('/').substringAfterLast("/")


                    // Log the extracted ID string to debug
                    Log.d("Omare", "Extracted ID: $idString")

                    if (idString.isNotBlank()) {
                        val id = idString.toIntOrNull()
                        if (id == null) {
                            Log.e("PokemonRemoteMediator", "Invalid ID format, skipping Pokémon: $url")
                            return@mapNotNull null  // Skip if ID cannot be parsed
                        }
                        // Proceed with the valid ID
                        Log.d("PokemonRemoteMediator", "Successfully extracted ID: $id")

                        // Fetch detailed data for each Pokémon with error handling
                        val detailsResponse = try {
                            pokemonApi.getPokemonDetails(id)  // Attempt to fetch Pokémon details
                        } catch (e: Exception) {
                            Log.e("PokemonRemoteMediator", "Failed to fetch details for Pokémon ID: $id", e)
                            return@mapNotNull null  // Skip this Pokémon if fetching details fails
                        }

                        Log.d("Bored", "Details Response for ID $id: ${detailsResponse.toString()}")
                        Log.d("PokemonRemoteMediator", "Pokemon sprite URL: ${detailsResponse.sprites.front_default}")



                        // Convert details to PokemonEntity
                        detailsResponse.toPokemonEntity()


                    } else {
                        Log.e("PokemonRemoteMediator", "Extracted ID is blank, skipping Pokémon: $url")
                        return@mapNotNull null  // Skip if ID is blank
                    }
                } else {
                    Log.e("PokemonRemoteMediator", "URL format is invalid, skipping Pokémon: $url")
                    return@mapNotNull null  // Skip if URL is not in the expected format
                }
            }

            // Save data in the database
            pokemonDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    pokemonDb.dao.clearAll()  // Clear all entries if refreshing
                }
                pokemonDb.dao.upsertAll(pokemonEntities)  // Insert or update the list of Pokémon entities
            }

            // Return success with pagination status
            MediatorResult.Success(endOfPaginationReached = response.next == null)

        } catch(e: IOException) {
            MediatorResult.Error(e)  // Handle network-related errors
        } catch(e: retrofit2.HttpException) {
            MediatorResult.Error(e)  // Handle HTTP errors
        }
    }
}




