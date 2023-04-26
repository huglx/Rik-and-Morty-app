package cz.cvut.fit.biand.homework2.features.detail.data

import cz.cvut.fit.biand.homework2.features.domain.Character
import kotlinx.coroutines.flow.Flow

interface DetailLocalDataSource {
    fun getDetail(id: Int): Flow<Character?>

    suspend fun setFavourite(id: Int)
}