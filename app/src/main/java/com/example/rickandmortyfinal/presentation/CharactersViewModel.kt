package com.example.rickandmortyfinal.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.domain.usecase.GetAllFromPageUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class CharactersViewModel(
    private val getAllFromPageUseCase: GetAllFromPageUseCase
) : ViewModel() {

    private companion object {
        const val START_PAGE = 1
        const val END_PAGE = 42
    }

    private val _state = MutableLiveData<CharactersState>(CharactersState.Initial)
    private val characters: MutableList<Character> = mutableListOf()

    val state: LiveData<CharactersState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = CharactersState.Loading

            try {
                for (page in START_PAGE..END_PAGE)
                    characters.addAll(getAllFromPageUseCase(page))

                _state.value = CharactersState.Content(characters.toList())
            } catch (e: Exception) {
                _state.value = CharactersState.Error(e.localizedMessage.orEmpty())
            }

        }
    }
}