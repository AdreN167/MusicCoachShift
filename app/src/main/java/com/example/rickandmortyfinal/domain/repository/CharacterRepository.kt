package com.example.rickandmortyfinal.domain.repository

import com.example.rickandmortyfinal.domain.entity.Character

interface CharacterRepository {

    suspend fun getAll(): List<Character>

    suspend fun getById(id: Long): Character

    suspend fun getAllFromPage(page: Int): List<Character>
}