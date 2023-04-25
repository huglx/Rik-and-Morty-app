package cz.cvut.fit.biand.homework2.features.list.data.remote

import cz.cvut.fit.biand.homework2.features.list.data.CharacterRemoteDataSource
import cz.cvut.fit.biand.homework2.features.list.domain.Character

class CharacterRetrofitDataSource(
    private val characterApiDescription: CharacterApiDescription
): CharacterRemoteDataSource  {
    override suspend fun getCharacters(): List<Character> {
        return characterApiDescription.getCharacters().map {
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
