package com.example.rickandmortyfinal.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyfinal.databinding.CharactersItemBinding
import com.example.rickandmortyfinal.domain.entity.Character
import com.example.rickandmortyfinal.presentation.CharactersViewModel
import com.squareup.picasso.Picasso

class CharactersAdapter(
    private val characterClickListener: (Character) -> Unit
) : RecyclerView.Adapter<CharactersViewHolder>() {

    var characters: List<Character> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharactersItemBinding.inflate(inflater, parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characters[position], characterClickListener)
    }

    override fun getItemCount(): Int =
        characters.size
}

class CharactersViewHolder(
    private val binding: CharactersItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character, characterClickListener: (Character) -> Unit) {
        with(binding) {
            Picasso.get().load(character.image).into(imageCharacterView)
            nameCharacterView.text = character.name
        }

        itemView.setOnClickListener {
            characterClickListener(character)
        }
    }
}