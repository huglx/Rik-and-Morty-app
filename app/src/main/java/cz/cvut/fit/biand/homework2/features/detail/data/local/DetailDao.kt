package cz.cvut.fit.biand.homework2.features.detail.data.local

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.fit.biand.homework2.core.data.db.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {
    @Query("select * from character c where c.id=:id")
    fun getDetail(id: Int): Flow<CharacterEntity?>
    @Query("update character set isFavourite = true where id =:id")
    suspend fun setFavourite(id: Int)

    @Query("update character set isFavourite = false where id =:id")
    suspend fun unSetFavourite(id: Int)
}