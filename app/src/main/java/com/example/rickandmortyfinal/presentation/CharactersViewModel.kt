package com.example.rickandmortyfinal.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortyfinal.domain.usecase.GetAllFromPageUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class CharactersViewModel(
    private val getAllFromPageUseCase: GetAllFromPageUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CharactersState>(CharactersState.Initial)
    val state: LiveData<CharactersState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = CharactersState.Loading

            try {
                val characters: MutableList<Character> = mutableListOf()

                for (i in 1..4) {
                    characters.addAll(getAllFromPageUseCase(i))
                }

                _state.value = CharactersState.Content(characters.toList())
            } catch (e: Exception) {
                _state.value = CharactersState.Error(e.localizedMessage.orEmpty())
            }

        }
    }
}