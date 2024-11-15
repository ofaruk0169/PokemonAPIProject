package com.example.projectpokemon.data.remote

data class PokemonDto(
    val id: Int,
    val types: String,
    val abilities: String,
    val height: String,
    val spriteUrl: String?,
    val name: String,
    val url: String
)
