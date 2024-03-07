package com.example.rickadnmorty_62.di

import com.example.rickadnmorty_62.data.network.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single{
        Repository(get())
    }
}