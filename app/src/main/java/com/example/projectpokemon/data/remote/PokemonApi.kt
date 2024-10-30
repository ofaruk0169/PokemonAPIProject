package com.example.projectpokemon.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): PokemonResponse

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon"
    }
}