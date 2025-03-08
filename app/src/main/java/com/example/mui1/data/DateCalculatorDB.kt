package com.example.mui1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DateCalculation::class],
    version = 1
)
abstract class DateCalculatorDB : RoomDatabase() {
    abstract val dateCalculationDao : DateCalculationDAO

    companion object {
        private var INSTANCE: DateCalculatorDB? = null
        fun getInstance(context: Context): DateCalculatorDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): DateCalculatorDB {
            return Room.databaseBuilder(
                context.applicationContext,
                DateCalculatorDB::class.java, "main.db"
            ).build()
        }
    }
}