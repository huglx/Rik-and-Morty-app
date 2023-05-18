package cz.cvut.fit.biand.homework2.features.detail.domain
import cz.cvut.fit.biand.homework2.features.domain.Character

data class DBResponse(
    val data: Character,
    val isFromDB: Boolean
)
