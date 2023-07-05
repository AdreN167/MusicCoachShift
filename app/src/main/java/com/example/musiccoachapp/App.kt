package com.example.musiccoachapp

import android.app.Application
import com.example.musiccoachapp.data.CharacterService

class App : Application() {
    val characterService = CharacterService()
}