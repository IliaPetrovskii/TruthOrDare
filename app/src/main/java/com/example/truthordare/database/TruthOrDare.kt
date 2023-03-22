package com.example.truthordare.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "truth_or_dare_table")
data class TruthOrDare(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    var id: Int = 0,

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "type")
    val type: String = "Truth",

    @ColumnInfo(name = "isUsers")
    val isUsers: Boolean = false
)
