package com.example.rickadnmorty_62.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickadnmorty_62.data.network.Repository
import com.example.rickadnmorty_62.utils.Resource
import com.example.rickadnmorty_62.data.model.Character

class SharedViewModel (private val repository: Repository):ViewModel() {
    fun getCharacters():LiveData<Resource<List<Character>>> = repository.getCharacters()
}