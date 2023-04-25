package cz.cvut.fit.biand.homework2.features.list.data.remote

import cz.cvut.fit.biand.homework2.features.list.data.CharactersRemoteDataSource
import cz.cvut.fit.biand.homework2.features.list.domain.Character

class CharactersRetrofitDataSource(
    private val charactersApiDescription: CharactersApiDescription
): CharactersRemoteDataSource  {
    override suspend fun getCharacters(): List<Character> {
        return charactersApiDescription.getCharacters().results.map {
            it.toCharacter()
        }
    }
}



private fun CharacterApi.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.name,
        location = location.name,
        image = image,
    )
}
