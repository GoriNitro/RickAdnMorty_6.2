package com.example.rickadnmorty_62.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.rickadnmorty_62.data.base.BaseRepository
import com.example.rickadnmorty_62.utils.Resource
import com.example.rickadnmorty_62.data.model.Character
import com.example.rickadnmorty_62.di.dispatcherIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository(private val api: SimpleApi): BaseRepository(api) {
    suspend fun getCharacters(): LiveData<Resource<List<Character>>> {
        return withContext(Dispatchers.IO) {
            val resultLiveData = MutableLiveData<Resource<List<Character>>>()
            try {
                val response = api.getCharacters()
                if (response.isSuccessful) {
                    val characters = response.body()?.results ?: emptyList()
                    resultLiveData.postValue(Resource.Success(characters))
                } else {
                    resultLiveData.postValue(Resource.Error("Failed to fetch characters"))
                }
            } catch (e: Exception) {
                resultLiveData.postValue(Resource.Error("Error occurred"))
            }
            resultLiveData
        }
    }
}