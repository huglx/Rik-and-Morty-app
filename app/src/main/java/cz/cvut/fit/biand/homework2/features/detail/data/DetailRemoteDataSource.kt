package cz.cvut.fit.biand.homework2.features.detail.data

import cz.cvut.fit.biand.homework2.features.domain.Character
import kotlinx.coroutines.flow.Flow

interface DetailRemoteDataSource {
    suspend fun getCharacterById(id: Int): Flow<Character>
}