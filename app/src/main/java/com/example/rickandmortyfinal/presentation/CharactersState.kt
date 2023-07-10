package com.example.rickandmortyfinal.presentation

import com.example.rickandmortyfinal.domain.entity.Character

sealed interface CharactersState {

    object Initial: CharactersState

    object Loading: CharactersState

    data class Content(val items: List<Character>) : CharactersState

    data class Error(val msg: String) : CharactersState
}