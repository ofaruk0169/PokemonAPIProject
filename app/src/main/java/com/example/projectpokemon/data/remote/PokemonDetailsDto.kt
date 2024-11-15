package com.example.projectpokemon.data.remote

data class PokemonDetailsDto(
    val id: Int,
    val name: String,
    val height: Int,
    val abilities: List<AbilityDto>,
    val types: List<TypeDto>,
    val sprites: SpriteDto
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
    val front_default: String?
)