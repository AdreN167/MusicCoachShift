package com.example.rickandmortyfinal.domain.usecase

import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.domain.repository.CharacterRepository

class GetAllCharactersUseCase(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(): List<Character> =
        repository.getAll()
}