package com.example.projectpokemon.data.mappers

import com.example.projectpokemon.data.local.PokemonEntity
import com.example.projectpokemon.data.remote.PokemonDetailsDto
import com.example.projectpokemon.data.remote.PokemonDto
import com.example.projectpokemon.domain.Pokemon


data class SpriteDto(
    val frontDefault: String? = null  // Make it nullable
)

fun PokemonDetailsDto.toPokemonEntity(): PokemonEntity {

    // Safely handle null values for sprites
    val frontSprite = sprites?.front_default ?: "default_sprite_url"  // Provide fallback if sprite is null

    return PokemonEntity(
        id = id,
        name = name,
        types = types.joinToString { it.type.name }, // Convert types list to comma-separated string
        abilities = abilities.joinToString { it.ability.name }, // Convert abilities list to comma-separated string
        height = "$height",  // Convert height to string format
        spriteUrl = frontSprite // Use the frontSprite variable with the fallback value

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