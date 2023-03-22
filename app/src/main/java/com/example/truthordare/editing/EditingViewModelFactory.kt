package com.example.truthordare.editing

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.truthordare.database.TruthOrDareDao

class EditingViewModelFactory(private val dao: TruthOrDareDao,
                              private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditingViewModel::class.java)) {
            return EditingViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}