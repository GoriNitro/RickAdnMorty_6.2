package com.example.rickadnmorty_62.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickadnmorty_62.R
import com.example.rickadnmorty_62.databinding.FragmentListBinding
import com.example.rickadnmorty_62.model.Character
import com.example.rickadnmorty_62.ui.BaseFragment
import com.example.rickadnmorty_62.ui.SharedViewModel
import com.example.rickadnmorty_62.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment() {

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }
    private val sharedViewModel: SharedViewModel by viewModels()

    private val adapter by lazy {
        CharacterAdapter(this::onCLick)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getCharacters().stateHandler(
            success = {
                adapter.submitList(it)
            },
            state = { state ->
                binding.progressCircular.isVisible = state is Resource.Loading
            }
        )
    }

    private fun onCLick(character: Character) {
        val bundle = bundleOf(
            "name" to character.name,
            "status" to character.status,
            "species" to character.species,
            "origin" to character.origin.name,
            "location" to character.location.name,
            "image" to character.image,
        )
        findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }
}