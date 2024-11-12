package com.example.projectpokemon.data.remote

import android.util.Log
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): PokemonResponse




    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int,
    ): PokemonDetailsDto



    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}