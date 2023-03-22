package com.example.truthordare.game

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.truthordare.R
import com.example.truthordare.database.TruthOrDareDatabase
import com.example.truthordare.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false)
        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareDatabase.getInstance(application).getTruthOrDareDao()
        val viewModelFactory = GameViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
        binding.backButton.setOnClickListener {
            this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToStartFragment())
        }
        binding.truthButton.setOnClickListener {
            viewModel.nextPosition("Truth")
        }
        binding.dareButton.setOnClickListener {
            viewModel.nextPosition("Dare")
        }
        viewModel.positions.observe(viewLifecycleOwner, Observer {  })
        viewModel.activePositionText.observe(viewLifecycleOwner, Observer { text -> binding.optionTextView.text = text })
        return binding.root
    }
}