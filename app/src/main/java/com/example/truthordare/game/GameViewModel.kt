package com.example.truthordare.game

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truthordare.database.TruthOrDare
import com.example.truthordare.database.TruthOrDareDao

class GameViewModel(val dao: TruthOrDareDao,
                    application: Application
) : ViewModel() {

    val positions: MutableLiveData<MutableList<Pair<TruthOrDare, TruthOrDare>>> = MutableLiveData()
    val activePositionText: MutableLiveData<String> = MutableLiveData("")

    init {
        fillPositions()
    }

    private  fun fillPositions() {
        val truths = dao.getByType("Truth")
        val dares = dao.getByType("Dare")
        truths.shuffle()
        dares.shuffle()
        positions.value = truths.zip(dares).toMutableList()
    }

    fun nextPosition(type: String) {
        if (positions.value.isNullOrEmpty())
            fillPositions()
        val text : String = if (type == "Truth")
            positions.value?.first()?.first?.text ?: "Error"
        else
            positions.value?.first()?.second?.text ?: "Error"
        positions.value?.removeFirst()
        activePositionText.value = text
    }
}