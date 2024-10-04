package com.example.projectpokemon.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.projectpokemon.data.local.PokemonDatabase
import com.example.projectpokemon.data.local.PokemonEntity
import com.example.projectpokemon.data.remote.PokemonApi
import com.example.projectpokemon.data.remote.PokemonRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemons.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(PokemonApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePokemonPager(pokemonDb: PokemonDatabase, pokemonApi: PokemonApi): Pager<Int, PokemonEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(
                pokemonDb = pokemonDb,
                pokemonApi = pokemonApi
            ),
            pagingSourceFactory = {
                pokemonDb.dao.pagingSource()
            }
        )
    }
}