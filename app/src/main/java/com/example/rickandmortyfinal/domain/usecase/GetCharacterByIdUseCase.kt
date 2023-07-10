package com.example.rickandmortyfinal.domain.usecase

import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.domain.repository.CharacterRepository

class GetCharacterByIdUseCase(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(characterId: Long) : Character =
        repository.getById(characterId)
}