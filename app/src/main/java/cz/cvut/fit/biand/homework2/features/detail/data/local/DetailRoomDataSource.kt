package cz.cvut.fit.biand.homework2.features.detail.data.local

import cz.cvut.fit.biand.homework2.features.detail.data.DetailLocalDataSource
import cz.cvut.fit.biand.homework2.features.domain.Character
import cz.cvut.fit.biand.homework2.features.domain.toCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailRoomDataSource(
    private val detailDao: DetailDao
): DetailLocalDataSource {

    override fun getDetail(id: Int): Flow<Character?> {
        return detailDao.getDetail(id).map {
            it?.toCharacter()
        }
    }
}
