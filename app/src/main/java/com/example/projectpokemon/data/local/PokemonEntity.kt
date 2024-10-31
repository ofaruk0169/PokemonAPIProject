package com.example.projectpokemon.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val types: String,
    val abilities: String,
    val height: String,
    val spriteUrl: String?
)
