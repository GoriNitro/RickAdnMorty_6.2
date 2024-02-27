package com.example.rickadnmorty_62.remote

import com.example.rickadnmorty_62.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int):Response<CharacterList>
}