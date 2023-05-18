package cz.cvut.fit.biand.homework2.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
//same as for api data class
@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    var isFavourite: Boolean = false
)