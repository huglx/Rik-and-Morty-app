package cz.cvut.fit.biand.homework2.features.detail.data.remote

import cz.cvut.fit.biand.homework2.features.detail.data.DetailRemoteDataSource
import cz.cvut.fit.biand.homework2.features.domain.Character
import cz.cvut.fit.biand.homework2.features.domain.toCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailRetrofitDataSource(
    private val detailApiDescription: DetailApiDescription
): DetailRemoteDataSource {
    override suspend fun getCharacterById(id: Int): Flow<Character> = flow {
        val char = detailApiDescription.getCharacterById(id).toCharacter()
        emit(char)
    }

}