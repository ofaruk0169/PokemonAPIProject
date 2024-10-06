package com.example.projectpokemon.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val types: String?, //"tagline" in Phillip video
    val abilities: String?, //"description" in Phillip video
    val height: String?, //"first_brewed" in Phillip video
    val spriteUrl: String?? //"image_url" in Phillip video
)
