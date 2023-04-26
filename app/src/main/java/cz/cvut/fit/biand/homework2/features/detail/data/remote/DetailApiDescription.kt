package cz.cvut.fit.biand.homework2.features.detail.data.remote

import cz.cvut.fit.biand.homework2.core.data.api.CharacterApi
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApiDescription {
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id")id: Int): CharacterApi
}