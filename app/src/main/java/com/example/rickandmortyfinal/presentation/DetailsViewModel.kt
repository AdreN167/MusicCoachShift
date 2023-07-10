package com.example.rickandmortyfinal.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyfinal.domain.usecase.GetCharacterByIdUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel(
    private val useCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val _state: MutableLiveData<DetailsState> = MutableLiveData(DetailsState.Initial)
    val state: LiveData<DetailsState> = _state

    fun loadData(id: Long) {
        viewModelScope.launch {
            _state.value = DetailsState.Loading

            try {
                val character = useCase(id)
                _state.value = DetailsState.Content(character)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.value = DetailsState.Error(e.message.toString())
            }
        }
    }
}