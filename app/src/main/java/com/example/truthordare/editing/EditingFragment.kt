package com.example.truthordare.editing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.truthordare.R
import com.example.truthordare.database.TruthOrDareDatabase
import com.example.truthordare.databinding.FragmentEditingBinding


class EditingFragment : Fragment() {

    companion object {
        fun newInstance() = EditingFragment()
    }

    private lateinit var viewModel: EditingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEditingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_editing, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareDatabase.getInstance(application).getTruthOrDareDao()
        val viewModelFactory = EditingViewModelFactory(dao, application)
        val idArg: Int = arguments?.getInt("id") ?: 0
        val typeArg: String = arguments?.getString("type") ?: "Truth"
        val textArg: String = arguments?.getString("text") ?: ""
        viewModel = ViewModelProvider(this, viewModelFactory)[EditingViewModel::class.java]
        viewModel.type.observe(viewLifecycleOwner, Observer { type ->
            if (type.equals("Dare"))
                binding.dareRadioButton.isChecked = true
            else
                binding.truthRadioButton.isChecked = true
        })
        viewModel.id = idArg
        viewModel.type.value = typeArg
        viewModel.text.value = textArg
        binding.textInputLayout.editText?.setText(textArg)
        binding.textInputEditLayout.doOnTextChanged{ text, _, _, _ ->
            viewModel.text.value = text.toString()
        }
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.truthRadioButton.id -> viewModel.type.value = "Truth"
                binding.dareRadioButton.id -> viewModel.type.value = "Dare"
            }
        }
        binding.acceptButton.setOnClickListener {
            if (!binding.textInputLayout.editText?.text.isNullOrEmpty()) {
                viewModel.text.value = binding.textInputLayout.editText!!.text.toString()
                viewModel.applyChange()
                this.findNavController()
                    .navigate(EditingFragmentDirections.actionEditingFragmentToOptionsFragment())
            }
        }
        binding.cancelButton.setOnClickListener {
            this.findNavController()
                .navigate(EditingFragmentDirections.actionEditingFragmentToOptionsFragment())
        }
        return binding.root
    }
}