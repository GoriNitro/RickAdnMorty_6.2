package com.example.rickadnmorty_62.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickadnmorty_62.databinding.ItemCharacterBinding
import com.example.rickadnmorty_62.data.model.Character

class CharacterAdapter(
    private val onClick: (Character) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Character, CharacterViewHolder>(
    CartoonDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onClick: (Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) = with(binding) {
        binding.txtIdCharacter.text = character.id.toString()
        binding.name.text = character.name
        binding.imgCharacter.load(character.image) {
            crossfade(true)
        }
        itemView.setOnClickListener {
            onClick
        }
    }
}

class CartoonDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}