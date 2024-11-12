package com.example.projectpokemon.data.remote

import com.squareup.moshi.Json

data class PokemonDetailsDto(
    val id: Int,
    val name: String,
    val height: Int,
    val abilities: List<AbilityDto>,
    val types: List<TypeDto>,
    @Json(name = "sprites") val sprite: SpriteDto
)

data class AbilityDto(
    val ability: AbilityInfoDto
)

data class AbilityInfoDto(
    val name: String
)

data class TypeDto(
    val type: TypeInfoDto
)

data class TypeInfoDto(
    val name: String
)

data class SpriteDto(
    @Json(name = "front_default") val frontDefault: String?
)