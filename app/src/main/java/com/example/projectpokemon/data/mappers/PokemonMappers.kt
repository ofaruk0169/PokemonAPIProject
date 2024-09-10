package com.example.projectpokemon.data.mappers

import com.example.projectpokemon.data.local.PokeEntity
import com.example.projectpokemon.data.remote.PokeDto
import com.example.projectpokemon.domain.Pokemon

fun PokeDto.toPokeEntity(): PokeEntity {
    return PokeEntity(
        id = id,
        name = name,
        types = types,
        abilities = abilities,
        height = height,
        spriteUrl = spriteUrl
    )
}

fun PokeEntity.toPoke(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        types = types,
        abilities = abilities,
        height = height,
        spriteUrl = spriteUrl
    )
}