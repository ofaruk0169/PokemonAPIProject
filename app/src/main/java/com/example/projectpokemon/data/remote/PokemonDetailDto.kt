package com.example.projectpokemon.data.remote

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val sprites: Sprites,
    val types: List<TypeEntry>,
    val abilities: List<AbilityEntry>
)

data class Sprites(
    val front_default: String?
)

data class TypeEntry(
    val type: Type
)

data class Type(
    val name: String
)

data class AbilityEntry(
    val ability: Ability
)

data class Ability(
    val name: String
)