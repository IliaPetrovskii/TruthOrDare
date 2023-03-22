package com.example.truthordare.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.truthordare.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingData(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingData(context)
        }
    }

    private suspend fun fillWithStartingData(context: Context) {
        val dao = TruthOrDareDatabase.getInstance(context).getTruthOrDareDao()
        try {
            val data = loadJSONArray(context)
            for (i in 0 until data.length()) {
                val item = data.getJSONObject(i)
                val text = item.getString("text")
                val type = item.getString("type")
                val entity = TruthOrDare(
                    0, text, type, false
                )
                dao.insert(entity)
            }
        }
        catch (e: JSONException) {
            Log.e("ToD_APP", "JSONException", e);
        }
    }

        private fun loadJSONArray(context: Context): JSONArray {
        val inputStream = context.resources.openRawResource(R.raw.data)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}