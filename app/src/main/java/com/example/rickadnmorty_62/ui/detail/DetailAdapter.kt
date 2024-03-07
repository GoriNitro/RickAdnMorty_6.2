package com.example.rickadnmorty_62.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickadnmorty_62.databinding.ItemEpisodeBinding
import com.example.rickadnmorty_62.data.model.Character

class DetailAdapter(private val onClick: (Character) -> Unit, var list: List<Character>) :
    ListAdapter<Character, DetailViewHolder>(CharacterEpisodeCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = ItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DetailViewHolder(
    private val binding: ItemEpisodeBinding,
    private val onClick: (Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character) {
        with(binding) {
            tvEpisodes.text = character.id.toString()
            itemView.setOnClickListener {
                onClick(character)
            }
        }
    }
}


class CharacterEpisodeCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}