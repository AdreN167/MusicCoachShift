package com.example.rickandmortyfinal.domain.entity

import com.example.rickandmortyfinal.data.CharacterModel

data class RequestResult(
    val info: Info?,
    val results: List<CharacterModel>
)