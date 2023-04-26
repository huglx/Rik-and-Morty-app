package cz.cvut.fit.biand.homework2.features.list.data
import cz.cvut.fit.biand.homework2.features.domain.Character

interface CharactersLocalDataSource {
    suspend fun getCharacters(): List<Character>

    suspend fun insert(characters: List<Character>)
 }