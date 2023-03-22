package com.example.truthordare.editing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.truthordare.database.TruthOrDare
import com.example.truthordare.database.TruthOrDareDao

class EditingViewModel(
    val dao: TruthOrDareDao,
    application: Application
) : AndroidViewModel(application) {

    var id: Int = 0
    val text: MutableLiveData<String> = MutableLiveData()
    val type: MutableLiveData<String> = MutableLiveData()

    private fun insert() {
        dao.insert(TruthOrDare(0, text.value!!, type.value!!, true))
    }
    private fun update() {
        dao.update(TruthOrDare(id, text.value!!, type.value!!, true))
    }

    fun applyChange(){
        if (id == 0)
            insert()
        else
            update()
    }
}