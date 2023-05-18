package cz.cvut.fit.biand.homework2.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.cvut.fit.biand.homework2.features.detail.data.local.DetailDao
import cz.cvut.fit.biand.homework2.features.favourites.data.local.FavouriteDao
import cz.cvut.fit.biand.homework2.features.list.data.local.ListDao

@Database(version = 2, entities = [CharacterEntity::class])
abstract class CharactersDB: RoomDatabase() {
    abstract fun listDB(): ListDao
    abstract fun detailDB(): DetailDao
    abstract fun favouriteDB(): FavouriteDao
    companion object {
        fun instance(context: Context): CharactersDB {
            return Room
                .databaseBuilder(context, CharactersDB::class.java, "characters.db")
                .fallbackToDestructiveMigration()
                .build()

        }
    }
}