package com.example.musiccoachapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musiccoachapp.Adapter.CharacterAdapter
import com.example.musiccoachapp.data.CharacterService
import com.example.musiccoachapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val characterService: CharacterService
        get() = (applicationContext as App).characterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        adapter = CharacterAdapter()
        adapter.data = characterService.getCharacters()

        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }
}