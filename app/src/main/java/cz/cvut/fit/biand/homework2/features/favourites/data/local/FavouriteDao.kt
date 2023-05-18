package cz.cvut.fit.biand.homework2.features.favourites.data.local

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.fit.biand.homework2.core.data.db.CharacterEntity

@Dao
interface FavouriteDao {
    @Query("select * from character where isFavourite=1")
    suspend fun getFavourites(): List<CharacterEntity>

}