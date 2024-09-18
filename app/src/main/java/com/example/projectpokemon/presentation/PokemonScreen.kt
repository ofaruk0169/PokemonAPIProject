package com.example.projectpokemon.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.example.projectpokemon.domain.Pokemon

@Composable
fun PokemonScreen(
    pokemons: LazyPagingItems<Pokemon>
) {
    val context = LocalContext.current
}