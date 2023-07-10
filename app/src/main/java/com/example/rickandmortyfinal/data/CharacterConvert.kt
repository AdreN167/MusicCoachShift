package com.example.rickandmortyfinal.data

import com.example.rickandmortyfinal.domain.entity.Character

class CharacterConvert {

    fun convert(from: CharacterModel): Character =
        with(from) {
            Character(
                id = id.toLong(),
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                origin = origin,
                location = location,
                image = image,
                episode = episode,
                url = url,
                created = created
            )
        }
}