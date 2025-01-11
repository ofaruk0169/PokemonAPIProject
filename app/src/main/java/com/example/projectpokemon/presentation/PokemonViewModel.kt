package com.example.projectpokemon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.projectpokemon.data.local.PokemonEntity
import com.example.projectpokemon.data.mappers.toPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    pager: Pager<Int, PokemonEntity>
): ViewModel() {
    val pokemonPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPokemon() }
        }
        .cachedIn(viewModelScope)
}