package com.example.truthordare.playeroptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.truthordare.R
import com.example.truthordare.database.TruthOrDareDatabase
import com.example.truthordare.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {

    companion object {
        fun newInstance() = OptionsFragment()
    }

    private lateinit var viewModel: OptionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOptionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_options, container, false)
        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareDatabase.getInstance(application).getTruthOrDareDao()
        val viewModelFactory = OptionsViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[OptionsViewModel::class.java]
        val adapter = OptionsAdapter(viewModel::delete)
        binding.recyclerView.adapter = adapter
        viewModel.positions.observe(viewLifecycleOwner, Observer { positions ->
            if (positions != null)
                adapter.data = positions
        })
        binding.addButton.setOnClickListener {
            this.findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToEditingFragment())
        }
        binding.backButton.setOnClickListener {
            this.findNavController().navigate(OptionsFragmentDirections.actionOptionsFragmentToStartFragment())
        }
        return binding.root
    }

}