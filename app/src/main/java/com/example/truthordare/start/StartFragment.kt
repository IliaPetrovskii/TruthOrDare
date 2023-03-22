package com.example.truthordare.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.truthordare.R
import com.example.truthordare.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentStartBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_start, container, false)
        binding.startButton.setOnClickListener {
            this.findNavController().navigate(StartFragmentDirections.actionStartFragmentToGameFragment())
        }
        binding.optionsButton.setOnClickListener {
            this.findNavController().navigate(StartFragmentDirections.actionStartFragmentToOptionsFragment())
        }
        return binding.root
    }

}