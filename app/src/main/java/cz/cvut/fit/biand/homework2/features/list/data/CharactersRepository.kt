package cz.cvut.fit.biand.homework2.features.list.data
import cz.cvut.fit.biand.homework2.features.list.domain.Character
class CharactersRepository(
    private val characterRemoteDataSource: CharacterRemoteDataSource
) {
    suspend fun getCharacters(): List<Character> {
        return characterRemoteDataSource.getCharacters()
    }
}