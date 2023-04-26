package cz.cvut.fit.biand.homework2.features.search.data.remote

import cz.cvut.fit.biand.homework2.core.data.api.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiDescription {
    @GET("character/")
    suspend fun getCharactersByName(@Query("name") name: String = ""): Results
}