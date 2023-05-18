package cz.cvut.fit.biand.homework2.features.favourites.data.local

import cz.cvut.fit.biand.homework2.features.domain.Character
import cz.cvut.fit.biand.homework2.features.domain.toCharacter
import cz.cvut.fit.biand.homework2.features.favourites.data.FavouriteLocalDataSource

class FavouriteRoomDataSource(
    private val favouriteDao: FavouriteDao
): FavouriteLocalDataSource {

    override suspend fun getFavorites(): List<Character> {
        return favouriteDao.getFavourites().map { item ->
            item.toCharacter()
        }
    }

}

