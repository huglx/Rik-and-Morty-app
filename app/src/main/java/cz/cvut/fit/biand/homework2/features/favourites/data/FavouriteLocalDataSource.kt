package cz.cvut.fit.biand.homework2.features.favourites.data
import cz.cvut.fit.biand.homework2.features.domain.Character

interface FavouriteLocalDataSource {
    suspend fun getFavorites(): List<Character>
}