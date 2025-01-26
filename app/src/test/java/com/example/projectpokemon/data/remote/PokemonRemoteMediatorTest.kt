package com.example.projectpokemon.data.remote

import com.example.projectpokemon.domain.Pokemon
import org.junit.Assert.*
import org.junit.Before

class PokemonRemoteMediatorTest {
    private lateinit var pokemonRemoteMediator: PokemonRemoteMediator

    @Before
    fun setUp() {
        pokemonRemoteMediator = PokemonRemoteMediator(
            //here we need to pass both the database and the API, did a litttle bit here so that's a start.

        )
    }
}