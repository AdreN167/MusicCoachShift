package com.example.musiccoachapp.data

class CharacterService {
    private var characters = mutableListOf<Character>()

    // TODO инициализация списка персонажей

    fun getCharacters() = characters
}

data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: NameAndURLObject,
    val location: NameAndURLObject,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)
