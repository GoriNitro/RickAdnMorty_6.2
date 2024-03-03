package com.example.rickadnmorty_62.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickadnmorty_62.remote.Repository
import com.example.rickadnmorty_62.utils.Resource
import com.example.rickadnmorty_62.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository:Repository):ViewModel() {
    fun getCharacters():LiveData<Resource<List<Character>>> = repository.getCharacters()
}