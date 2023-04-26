package cz.cvut.fit.biand.homework2.features.list.data.remote

import cz.cvut.fit.biand.homework2.features.list.data.CharactersRemoteDataSource
import cz.cvut.fit.biand.homework2.features.list.domain.Character
import cz.cvut.fit.biand.homework2.features.list.domain.toCharacter

class CharactersRetrofitDataSource(
    private val charactersApiDescription: CharactersApiDescription
): CharactersRemoteDataSource  {
    override suspend fun getCharacters(): List<Character> {
        return charactersApiDescription.getCharacters().results.map {
            it.toCharacter()
        }
    }
}
