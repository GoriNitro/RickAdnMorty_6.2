package com.example.rickadnmorty_62.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickadnmorty_62.R
import com.example.rickadnmorty_62.databinding.ItemCharacterBinding
import com.example.rickadnmorty_62.model.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listCharacters = emptyList<Character>()

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(character: Character) {
                binding.imgCharacter.load(character.image)
                binding.firstSeen.text = character.location.toString()
                binding.lastLoc.text = character.location.toString()
                binding.name.text = character.name
                binding.status.text = character.status
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listCharacters[position])
        holder.itemView.setOnClickListener{view ->
            view.findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
    }
    fun setCharacters(character: List<Character>) {
        listCharacters = character
        notifyDataSetChanged()
    }
}