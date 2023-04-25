package cz.cvut.fit.biand.homework2.coure.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.cvut.fit.biand.homework2.features.list.data.local.ListDao

@Database(version = 1, entities = [CharacterEntity::class])
abstract class CharactersDB: RoomDatabase() {
    abstract fun listDB(): ListDao

    companion object {
        fun instance(context: Context): CharactersDB {
            return Room.databaseBuilder(context, CharactersDB::class.java, "characters.db").build()
        }
    }
}