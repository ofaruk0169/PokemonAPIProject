package com.example.projectpokemon.data.remote

import com.squareup.moshi.Json

data class PokemonDto(
    val id: Int,
    val name: String,
    val types: List<TypeWrapper>, //"tagline" in Phillip video
    val abilities: List<AbilityWrapper>, //"description" in Phillip video
    val height: Int, //"first_brewed" in Phillip video
    val spriteUrl: String? //"image_url" in Phillip video
)

data class AbilityWrapper(
    val ability: Ability
)

data class Ability(
    val name: String
)

data class TypeWrapper(
    val type: Type
)

data class Type(
    val name: String
)

data class Sprites(
    @Json(name = "front_default")
    val spriteUrl: String?
)
