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
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyfinal.databinding.FragmentCharactersBinding
import com.example.rickandmortyfinal.databinding.FragmentDetailsBinding
import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.domain.usecase.GetCharacterByIdUseCase
import com.example.rickandmortyfinal.mainActivity
import com.example.rickandmortyfinal.presentation.DetailsState
import com.example.rickandmortyfinal.presentation.DetailsViewModel
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels {
        viewModelFactory {
            initializer {
                DetailsViewModel(GetCharacterByIdUseCase(mainActivity.repository))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::handleState)

        loadData()
    }

    private fun loadData() {
        viewModel.loadData(args.characterId)
    }

    private fun handleState(state: DetailsState) {
        when (state) {
            DetailsState.Initial    -> Unit
            DetailsState.Loading    -> showProgress()
            is DetailsState.Content -> showContent(state.character)
            is DetailsState.Error   -> showError(state.msg)
        }
    }

    private fun showContent(character: Character) {
        with(binding) {
            progressBar.isVisible = false
            errorContent.isVisible = false

            detailsContent.isVisible = true

            idCharacterView.text = character.id.toString()
            nameCharacterView.text = character.name
            genderValue.text = character.gender
            speciesValue.text = character.species
            statusValue.text = character.status
            Picasso.get().load(character.image).into(imageCharacterView)
        }
    }

    private fun showProgress() {
        with(binding) {
            errorContent.isVisible = false
            detailsContent.isVisible = false

            progressBar.isVisible = true
        }
    }

    private fun showError(message: String) {
        with(binding) {
            progressBar.isVisible = false
            detailsContent.isVisible = false

            errorContent.isVisible = true
            errorText.text = message
            errorButton.setOnClickListener { loadData() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}