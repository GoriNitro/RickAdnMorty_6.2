package com.example.rickadnmorty_62.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.example.rickadnmorty_62.databinding.FragmentDetailBinding
import com.example.rickadnmorty_62.data.base.BaseFragment
import com.example.rickadnmorty_62.ui.SharedViewModel

class DetailFragment : BaseFragment() {
    private val binding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
    }

    private fun getArgs() {
        arguments?.let { bundle ->
            val name = bundle.getString("name")
            val location = bundle.getString("location")
            val photoUrl = bundle.getString("image")
            val status = bundle.getString("status")
            val gender = bundle.getString("gender")
            val species = bundle.getString("species")
            val origin = bundle.getString("origin")
            val episode = bundle.getString("episodes")

            binding.imgCharacter.load(photoUrl)
            binding.txtName.text = name
            binding.txtStatus.text = status
            binding.txtGender.text = gender
            binding.txtSpecie.text = species
            binding.txtOrigin.text = origin.toString()
            binding.txtLocation.text = location.toString()
            binding.txtEpisodes.text = episode.toString()
        }
    }
}