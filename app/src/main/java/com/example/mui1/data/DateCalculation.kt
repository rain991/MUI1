package com.example.mui1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "dateCalculation"
)
data class DateCalculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "InputDate")
    val InputDate: Long,
    @ColumnInfo(name = "CalculatedDate")
    val CalculatedDate: Long,
    @ColumnInfo(name = "TimeOption")
    val TimeOption : String
)