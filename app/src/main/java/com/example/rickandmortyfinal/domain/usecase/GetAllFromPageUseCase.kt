package com.example.rickandmortyfinal.domain.usecase

import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.domain.repository.CharacterRepository

class GetAllFromPageUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int): List<Character> =
        repository.getAllFromPage(page)
}