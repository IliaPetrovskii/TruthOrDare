package com.example.truthordare.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TruthOrDare::class], version = 1, exportSchema = false)
abstract class TruthOrDareDatabase : RoomDatabase() {

    abstract fun getTruthOrDareDao(): TruthOrDareDao

    companion object {
        @Volatile
        private var INSTANCE: TruthOrDareDatabase? = null

        fun getInstance(context: Context): TruthOrDareDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TruthOrDareDatabase::class.java, "truth_or_dare_db"
                    )
                        .allowMainThreadQueries()
                        .addCallback(StartingData(context))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}