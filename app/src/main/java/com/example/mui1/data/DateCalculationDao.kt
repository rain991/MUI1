package com.example.mui1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DateCalculationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(DateCalculation: DateCalculation)

    @Update
    suspend fun update(DateCalculation: DateCalculation)

    @Delete
    suspend fun deleteItem(DateCalculation: DateCalculation)

    @Query("SELECT * FROM dateCalculation")
    fun getAll(): Flow<List<DateCalculation>>
}