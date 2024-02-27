package com.example.rickadnmorty_62.remote

import com.example.rickadnmorty_62.model.CharacterList
import retrofit2.Response

class Repository {
    suspend fun getCharacters(page: Int): Response<CharacterList> {
        return AppModule().api.getCharacters(page)
    }
}