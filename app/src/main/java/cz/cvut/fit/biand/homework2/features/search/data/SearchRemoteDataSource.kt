package cz.cvut.fit.biand.homework2.features.search.data
import cz.cvut.fit.biand.homework2.features.list.domain.Character
interface SearchRemoteDataSource {
    suspend fun getCharactersByName(name: String): List<Character>
}