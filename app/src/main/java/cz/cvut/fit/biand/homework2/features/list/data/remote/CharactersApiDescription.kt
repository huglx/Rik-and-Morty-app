package cz.cvut.fit.biand.homework2.features.list.data.remote

import cz.cvut.fit.biand.homework2.core.data.api.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiDescription {
    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int = 1): Results
}