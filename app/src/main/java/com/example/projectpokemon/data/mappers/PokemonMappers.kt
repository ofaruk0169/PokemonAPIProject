package com.example.projectpokemon.data.mappers

import com.example.projectpokemon.data.local.PokemonEntity
import com.example.projectpokemon.data.remote.PokemonDto
import com.example.projectpokemon.domain.Pokemon

fun PokemonDto.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        types = types,
        abilities = abilities,
        height = height,
        spriteUrl = spriteUrl
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