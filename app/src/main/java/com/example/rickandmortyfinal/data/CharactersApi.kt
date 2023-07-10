package com.example.rickandmortyfinal.data

import com.example.rickandmortyfinal.domain.entity.RequestResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("character")
    suspend fun getAll(): RequestResult

    @GET("character/")
    suspend fun getAllFromPage(@Query("page") page: Int): RequestResult

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Long): CharacterModel

}