package com.example.rickandmortyfinal.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickandmortyfinal.databinding.FragmentCharactersBinding
import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortyfinal.domain.usecase.GetAllFromPageUseCase
import com.example.rickandmortyfinal.mainActivity
import com.example.rickandmortyfinal.presentation.CharactersState
import com.example.rickandmortyfinal.presentation.CharactersViewModel

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels {
        viewModelFactory {
            initializer {
                CharactersViewModel(GetAllFromPageUseCase(mainActivity.repository))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainList.adapter = CharactersAdapter(::handleCharacterClick)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        viewModel.loadData()
    }

    private fun handleState(state: CharactersState) {
        when(state) {
            CharactersState.Initial    -> Unit
            CharactersState.Loading    -> showProgress()
            is CharactersState.Content -> showContent(state.items)
            is CharactersState.Error   -> showError(state.msg)
        }
    }

    private fun handleCharacterClick(character: Character) {
        mainActivity.openDetails(characterId = character.id)
    }

    private fun showContent(characters: List<Character>) {
        with(binding) {
            progressBar.isVisible = false
            errorContent.isVisible = false

            mainContent.isVisible = true
            (mainList.adapter as? CharactersAdapter)?.characters = characters
        }
    }

    private fun showProgress() {
        with(binding) {
            errorContent.isVisible = false
            mainContent.isVisible = false

            progressBar.isVisible = true
        }
    }

    private fun showError(message: String) {
        with(binding) {
            mainContent.isVisible = false
            progressBar.isVisible = false

            errorContent.isVisible = true
            errorText.text = message
            errorButton.setOnClickListener {
                viewModel.loadData() // еще раз пытаемся загрузить данные
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.mainList.adapter = null
        _binding = null
    }
}