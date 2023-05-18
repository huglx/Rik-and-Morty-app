package cz.cvut.fit.biand.homework2.features.list.data
import cz.cvut.fit.biand.homework2.features.domain.Character

class CharactersRepository(
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource
) {
    suspend fun getCharacters(): List<Character> { //make only 1 api call the first time you open the application
        return charactersLocalDataSource.getCharacters().ifEmpty {
            val apiResponse = charactersRemoteDataSource.getCharacters()
            charactersLocalDataSource.insert(apiResponse)
            apiResponse
        }
    }
}