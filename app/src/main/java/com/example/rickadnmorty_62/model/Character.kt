package com.example.rickadnmorty_62.model

data class Location(
    var name: String
)

data class Character(
    var name: String,
    var status: String,
    var location: Location,
    var image: String
)

data class CharacterList(
    var results: List<Character>
)
