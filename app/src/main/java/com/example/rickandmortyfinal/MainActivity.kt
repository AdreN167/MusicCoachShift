package com.example.rickandmortyfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.rickandmortyfinal.data.CharacterRepositoryImpl
import com.example.rickandmortyfinal.domain.repository.CharacterRepository
import com.example.rickandmortyfinal.screen.CharactersFragmentDirections

class MainActivity : AppCompatActivity() {

    val repository: CharacterRepository = CharacterRepositoryImpl()

    private val navController get() = findNavController(R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openDetails(characterId: Long) {
        val action = CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(characterId)
        navController.navigate(action)
    }
}