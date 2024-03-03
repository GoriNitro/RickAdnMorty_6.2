package com.example.rickadnmorty_62.ui

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.rickadnmorty_62.utils.Resource

abstract class BaseFragment : Fragment() {

    fun <T> LiveData<Resource<T>>.stateHandler(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit)? = null
    ) {
        observe(requireActivity()) { res ->
            state?.invoke(res)
            when (res) {
                is Resource.Error -> {
                    Toast.makeText(requireActivity(), res.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (res.data != null) {
                        success(res.data)
                    }
                }
            }
        }
    }
}