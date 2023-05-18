package cz.cvut.fit.biand.homework2.features.search.data.remote

import cz.cvut.fit.biand.homework2.features.domain.Character
import cz.cvut.fit.biand.homework2.features.domain.toCharacter
import cz.cvut.fit.biand.homework2.features.search.data.SearchRemoteDataSource

class SearchRetrofitDataSource(
    private val searchApiDescription: SearchApiDescription
): SearchRemoteDataSource {
    override suspend fun getCharactersByName(name: String): List<Character> {
        return searchApiDescription.getCharactersByName(name).results.map {
            it.toCharacter()
        }
    }
}