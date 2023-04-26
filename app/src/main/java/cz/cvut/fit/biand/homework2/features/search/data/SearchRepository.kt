package cz.cvut.fit.biand.homework2.features.search.data

import cz.cvut.fit.biand.homework2.features.list.domain.CharacterResponse

class SearchRepository(
    private val searchRemoteDataSource: SearchRemoteDataSource
) {
    suspend fun getCharactersByName(name: String): CharacterResponse {
        return try {
            val data = searchRemoteDataSource.getCharactersByName(name)
            CharacterResponse(data, true)
        }catch (_: Exception){
            CharacterResponse(listOf(), false)
        }
    }
}