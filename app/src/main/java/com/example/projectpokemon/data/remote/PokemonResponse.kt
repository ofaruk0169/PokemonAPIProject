package com.example.projectpokemon.data.remote

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDto>
)
