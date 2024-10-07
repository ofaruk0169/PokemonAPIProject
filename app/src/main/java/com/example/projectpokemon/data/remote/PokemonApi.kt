package com.example.projectpokemon.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<PokemonDto>

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}