package cz.cvut.fit.biand.homework2.features.favourites.data
import cz.cvut.fit.biand.homework2.features.domain.Character

class FavouriteRepository(
    private val favouriteLocalDataSource: FavouriteLocalDataSource
) {
    suspend fun getFavourite(): List<Character> {
        return favouriteLocalDataSource.getFavorites()
    }
}