package com.example.musiccoachapp.Adapter

import android.os.Build.VERSION_CODES.P
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musiccoachapp.data.Character
import com.example.musiccoachapp.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

interface CharacterActionListener {
    fun onCharacterClick(character: Character)
}

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    var data: List<Character> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)

        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = data[position]
        val context = holder.itemView.context

        with(holder.binding) {
            Picasso.get().load(character.image).into(imageCharacterView)
            nameCharacterView.text = character.name
        }
    }



    class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)
}