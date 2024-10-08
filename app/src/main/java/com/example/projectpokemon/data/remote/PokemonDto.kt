package com.example.projectpokemon.data.remote

data class PokemonDto(
    val id: Int,
    val name: String,
    val types: String, //"tagline" in Phillip video
    val abilities: String, //"description" in Phillip video
    val height: String, //"first_brewed" in Phillip video
    val spriteUrl: String? //"image_url" in Phillip video
)
