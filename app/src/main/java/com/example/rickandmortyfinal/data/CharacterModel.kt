package com.example.rickandmortyfinal.data

import com.example.rickandmortyfinal.domain.entity.Location
import com.google.gson.annotations.SerializedName

data class CharacterModel(
    val id: Int,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,

    @SerializedName("origin")
    val originLocation: Location?,

    @SerializedName("location")
    val currentLocation: Location?
)