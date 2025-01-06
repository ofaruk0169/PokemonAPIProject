package com.example.projectpokemon.domain

data class Pokemon(
    val id: Int,
    val name: String,
    val types: String,
    val abilities: String,
    val height: String,
    val spriteUrl: String?
)
