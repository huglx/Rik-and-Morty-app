package cz.cvut.fit.biand.homework2.core.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//data class CharactersApi is the same for several api calls, so I decided to leave it here in core
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