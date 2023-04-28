package cz.cvut.fit.biand.homework2.features.detail.data

import cz.cvut.fit.biand.homework2.features.detail.domain.DBResponse
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf

class DetailRepository(
    private val detailLocalDataSource: DetailLocalDataSource,
    private val detailRemoteDataSource: DetailRemoteDataSource
) {
    @OptIn(FlowPreview::class)
    fun getDetail(id: Int): Flow<DBResponse> {       //if we have that char. in DB, then we return the object immediately
        return detailLocalDataSource.getDetail(id)  //else we wait for response from API. Anyway we return flow of Char.
            .flatMapConcat { character ->
                if (character == null) {
                    flowOf(DBResponse(detailRemoteDataSource.getCharacterById(id).first(),false))
                } else {
                    flowOf(DBResponse(character, true))
                }
            }
    }
    suspend fun setFavourite(id: Int) {
        detailLocalDataSource.setFavourite(id)
    }

    suspend fun unSetFavourite(id: Int) {
        detailLocalDataSource.unSetFavourite(id)
    }
}