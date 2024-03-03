package com.example.rickadnmorty_62.remote

import androidx.lifecycle.LiveData
import com.example.rickadnmorty_62.ui.BaseRepository
import com.example.rickadnmorty_62.utils.Resource
import com.example.rickadnmorty_62.model.Character

class Repository(private val api: SimpleApi): BaseRepository(api) {

    fun getCharacters(): LiveData<Resource<List<Character>>> = performRequest {
        api.getCharacters().body()?.results ?: emptyList()
    }
}