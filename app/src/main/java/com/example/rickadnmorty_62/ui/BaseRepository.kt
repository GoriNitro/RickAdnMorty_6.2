package com.example.rickadnmorty_62.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickadnmorty_62.remote.SimpleApi
import com.example.rickadnmorty_62.utils.Resource
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository(private val api: SimpleApi) {

    protected fun <T> performRequest(apiCall: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
        }

}