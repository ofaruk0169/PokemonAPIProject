package com.example.projectpokemon.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val types: String, //"tagline" in Phillip video
    val abilities: String, //"description" in Phillip video
    val height: String, //"first_brewed" in Phillip video
    val spriteUrl: String? //"image_url" in Phillip video
)
