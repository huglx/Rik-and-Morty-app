package cz.cvut.fit.biand.homework2.features.list.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cz.cvut.fit.biand.homework2.core.data.db.CharacterEntity

@Dao
interface ListDao {
    @Query("SELECT * FROM character")
    suspend fun getCanteen(): List<CharacterEntity>

    @Insert
    suspend fun insert(canteen: List<CharacterEntity>)

    @Query("DELETE FROM character")
    suspend fun deleteAll()
}