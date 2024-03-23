package com.example.rickadnmorty_62.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickadnmorty_62.data.network.Repository
import com.example.rickadnmorty_62.utils.Resource
import com.example.rickadnmorty_62.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel(private val repository: Repository) : ViewModel() {
    fun getCharacters(): LiveData<Resource<List<Character>>> {
        val resultLiveData = MutableLiveData<Resource<List<Character>>>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characters = repository.getCharacters()
            } catch (e: Exception) {
                resultLiveData.postValue(Resource.Error("Error occurred"))
            }
        }
        return resultLiveData
    }
}
