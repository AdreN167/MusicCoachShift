package com.example.rickandmortyfinal.presentation

import com.example.rickandmortyfinal.domain.entity.Character

sealed interface DetailsState {

    object Initial: DetailsState

    object Loading: DetailsState

    data class Content(val character: Character) : DetailsState

    data class Error(val msg: String) : DetailsState
}