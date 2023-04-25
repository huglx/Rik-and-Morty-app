package cz.cvut.fit.biand.homework2.features.list.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("results")
data class Results(
    val results: List<CharacterApi>
)

@Serializable
data class CharacterApi(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String
)

@Serializable
data class Origin(
    val name: String
)

@Serializable
data class Location(
    val name: String
)