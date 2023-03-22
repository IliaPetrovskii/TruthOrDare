package com.example.truthordare.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TruthOrDareDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(position: TruthOrDare)

    @Update
    fun update(position: TruthOrDare)

    @Delete
    fun delete(position: TruthOrDare)

    @Query("SELECT * FROM truth_or_dare_table WHERE id = :key")
    fun getById(key: Long): TruthOrDare?

    @Query("SELECT * FROM truth_or_dare_table WHERE type = :type")
    fun getByType(type: String): MutableList<TruthOrDare>

    @Query("SELECT * FROM truth_or_dare_table WHERE isUsers = :isUsers")
    fun getByIsUser(isUsers: Boolean): LiveData<List<TruthOrDare>>

    @Query("SELECT * FROM truth_or_dare_table ORDER BY id ASC")
    fun getAll(): LiveData<List<TruthOrDare>>
}