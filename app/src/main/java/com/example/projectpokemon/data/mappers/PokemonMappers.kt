package com.example.projectpokemon.data.mappers

import com.example.projectpokemon.data.local.PokemonEntity
import com.example.projectpokemon.data.remote.PokemonDto
import com.example.projectpokemon.domain.Pokemon

fun PokemonDto.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        types = this.types?.joinToString(", ") { it.type.name } ?: "",
        abilities = this.abilities?.joinToString(", ") { it.ability.name } ?: "",
        height = this.height.toString(),
        spriteUrl = this?.spriteUrl
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        types = types,
        abilities = abilities,
        height = height,
        spriteUrl = spriteUrl
    )
}