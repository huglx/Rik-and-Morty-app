package cz.cvut.fit.biand.homework2.features.list.data
import cz.cvut.fit.biand.homework2.features.list.domain.Character

interface CharactersRemoteDataSource {
    suspend fun getCharacters(): List<Character>
}