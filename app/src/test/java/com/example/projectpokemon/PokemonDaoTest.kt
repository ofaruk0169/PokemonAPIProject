package com.example.projectpokemon

import android.content.ContextWrapper
import androidx.paging.PagingSource
import androidx.room.Room
import com.example.projectpokemon.data.local.PokemonDao
import com.example.projectpokemon.data.local.PokemonDatabase
import com.example.projectpokemon.data.local.PokemonEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PokemonDaoTest {

    private lateinit var database: PokemonDatabase
    private lateinit var dao: PokemonDao

    @Before
    fun setup() {
        // Use Room's in-memory database builder for local unit testing
        database = Room.inMemoryDatabaseBuilder(
            // You don't need the application context here
            object : ContextWrapper(null) {},
            PokemonDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao
    }

    @After
    fun tearDown() {
        // Close the database after tests
        database.close()
    }

    @Test
    fun insertAndRetrievePokemon() = runBlocking {
        // Given a PokemonEntity
        val pokemon = PokemonEntity(
            id = 1,
            name = "Pikachu",
            types = "Electric",
            abilities = "Static",
            height = "40",
            spriteUrl = "spriteUrl"
        )

        // When inserting into the database
        dao.upsertAll(listOf(pokemon))

        // Then retrieve and verify
        val allPokemons = dao.pagingSource().load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        if (allPokemons is PagingSource.LoadResult.Page) {
            assertEquals(1, allPokemons.data.size)
            assertEquals("Pikachu", allPokemons.data[0].name)
        } else {
            throw AssertionError("PagingSource load result is not a page")
        }
    }
}