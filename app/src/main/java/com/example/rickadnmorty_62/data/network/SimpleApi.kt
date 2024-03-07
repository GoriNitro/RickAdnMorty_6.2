package com.example.rickadnmorty_62.data.network

import com.example.rickadnmorty_62.data.model.BaseResponse
import com.example.rickadnmorty_62.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponse<Character>>
}