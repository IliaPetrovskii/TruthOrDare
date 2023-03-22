package com.example.truthordare.playeroptions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.truthordare.database.TruthOrDare
import com.example.truthordare.database.TruthOrDareDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class OptionsViewModel(val dao: TruthOrDareDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val positions = dao.getByIsUser(true)

    fun delete(position: TruthOrDare) {
        dao.delete(position)
    }

}