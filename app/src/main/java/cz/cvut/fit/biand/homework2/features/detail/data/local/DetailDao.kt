package cz.cvut.fit.biand.homework2.features.detail.data.local

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.fit.biand.homework2.core.data.db.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {
    @Query("select * from character c where c.id=:id")
    fun getDetail(id: Int): Flow<CharacterEntity>
}