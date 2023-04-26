package cz.cvut.fit.biand.homework2.features.detail.data

import cz.cvut.fit.biand.homework2.features.list.domain.Character
import kotlinx.coroutines.flow.Flow

class DetailRepository(
    private val detailLocalDataSource: DetailLocalDataSource
) {
    fun getDetail(id: Int): Flow<Character> {
        return detailLocalDataSource.getDetail(id)
    }
}