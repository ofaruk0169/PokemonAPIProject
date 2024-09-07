package com.example.projectpokemon.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApi {

    // we need to define the different end points we have or need to access our API , he only has one which is
    //"beers" but I just putting Pokemon here.
    //Maybe this is time to explore both APIs and find similarities between them
    @GET("pokemons")
    suspend fun getPokemons(
        @Query("page") page: Int,
        //API needs to support pagination 
        @Query("per_page") pageCount: Int
    ): List<PokeDto>
}