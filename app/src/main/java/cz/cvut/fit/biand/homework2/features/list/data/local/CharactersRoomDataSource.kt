package cz.cvut.fit.biand.homework2.features.list.data.local

import cz.cvut.fit.biand.homework2.core.data.db.CharacterEntity
import cz.cvut.fit.biand.homework2.features.list.data.CharactersLocalDataSource
import cz.cvut.fit.biand.homework2.features.list.domain.Character

class CharactersRoomDataSource(
    private val listDao: ListDao
): CharactersLocalDataSource {
    override suspend fun getCharacters(): List<Character> {
        return listDao.getCharacters().map {item ->
                item.toCharacter()
        }
    }

    override suspend fun insert(characters: List<Character>) {
        listDao.insert(characters.map {
            it.toCharacterEntity()
        })
    }
}

private fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image,
    )
}

private fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        image = image,
    )
}
