package cz.cvut.fit.biand.homework2.features.detail.data

import cz.cvut.fit.biand.homework2.features.domain.Character
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf

class DetailRepository(
    private val detailLocalDataSource: DetailLocalDataSource,
    private val detailRemoteDataSource: DetailRemoteDataSource
) {
    @OptIn(FlowPreview::class)
    fun getDetail(id: Int): Flow<Character> {
        return detailLocalDataSource.getDetail(id)
            .flatMapConcat { character ->
                if (character == null) {
                    detailRemoteDataSource.getCharacterById(id)
                } else {
                    flowOf(character)
                }
            }
    }

    suspend fun setFavourite(id: Int) {
        detailLocalDataSource.setFavourite(id)
    }
}