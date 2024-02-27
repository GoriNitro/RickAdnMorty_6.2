package com.example.rickadnmorty_62.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickadnmorty_62.R
import com.example.rickadnmorty_62.ui.SharedViewModelFactory
import com.example.rickadnmorty_62.databinding.FragmentListBinding
import com.example.rickadnmorty_62.remote.Repository
import com.example.rickadnmorty_62.ui.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            Repository()
        )
    }

    private var adapter =  CharacterAdapter()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel.getCharacters(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        sharedViewModel.getCharacters(1)
        sharedViewModel.listCharacters.observe(viewLifecycleOwner,{response->
            if (response.isSuccessful){
                adapter.setCharacters(response.body()!!.results)
                binding.txtError.visibility = View.GONE
                binding.rvCharacters.visibility = View.VISIBLE
            }else{
                binding.txtError.text = getString(R.string.text_error, response.code())
                binding.txtError.visibility = View.VISIBLE
                binding.rvCharacters.visibility = View.INVISIBLE
            }
        })
        binding.apply {
            btnFilter.setOnClickListener {
                findNavController().navigate(R.id.action_listFragment_to_filterFragment)
                rvCharacters.adapter = adapter
            }
        }
//        findNavController().navigate(R.id.action_listFragment_to_filterFragment)
//        findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setupRecycler() = with(binding) {
        rvCharacters.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}