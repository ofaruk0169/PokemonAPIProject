package com.example.projectpokemon.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import coil.network.HttpException
import com.example.projectpokemon.data.local.PokemonDatabase
import com.example.projectpokemon.data.local.PokemonEntity
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

        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: retrofit2.HttpException) {
            MediatorResult.Error(e)
        }
    }
}


