package cz.cvut.fit.biand.homework2.features.list.data
import cz.cvut.fit.biand.homework2.features.list.domain.Character

class CharactersRepository(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource
) {
    suspend fun getCharacters(): List<Character> {
        return charactersLocalDataSource.getCharacters().ifEmpty {
            val apiResponse = charactersRemoteDataSource.getCharacters()
            charactersLocalDataSource.insert(apiResponse)
            apiResponse
        }
    }
}