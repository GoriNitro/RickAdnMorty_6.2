package com.example.rickadnmorty_62.di

import com.example.rickadnmorty_62.ui.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        SharedViewModel(get())
    }
}