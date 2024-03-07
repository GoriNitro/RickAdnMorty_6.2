package com.example.rickadnmorty_62.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickadnmorty_62.data.base.BaseRepository
import com.example.rickadnmorty_62.utils.Resource
import com.example.rickadnmorty_62.data.model.Character
import com.example.rickadnmorty_62.di.dispatcherIO

class Repository(private val api: SimpleApi): BaseRepository(api) {
    fun getCharacters(): LiveData<Resource<List<Character>>> = performRequest {
        api.getCharacters().body()?.results ?: emptyList()
    }
}