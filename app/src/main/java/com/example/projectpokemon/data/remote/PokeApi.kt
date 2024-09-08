package com.example.projectpokemon.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("page") page: Int,
        //API needs to support pagination 
        @Query("per_page") pageCount: Int
    ): List<PokeDto>

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}