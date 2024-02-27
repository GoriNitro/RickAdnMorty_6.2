package com.example.rickadnmorty_62.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickadnmorty_62.remote.Repository
import com.example.rickadnmorty_62.model.CharacterList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response


class SharedViewModel(val repository: Repository): ViewModel() {
    var listCharacters = MutableLiveData<Response<CharacterList>>()

    fun getCharacters(page: Int) {
        viewModelScope.launch {
            val characters = repository.getCharacters(page)
            listCharacters.value = characters
        }
    }
}