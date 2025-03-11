package com.example.mui1.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import java.util.Date
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras

class CalendarCalculatorViewModel(private val dateCalculationDao: DateCalculationDAO, private val dateCalculator: DateCalculator) : ViewModel() {


    suspend fun addDateCalculation(dateCalculation: DateCalculation) {
        dateCalculationDao.insertItem(dateCalculation)
    }

    fun getCalculationHistory(): Flow<List<DateCalculation>> {
        return dateCalculationDao.getAll()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val dateCalculationDao =
                    DateCalculatorDB.getInstance(application).dateCalculationDao
                return CalendarCalculatorViewModel(
                    dateCalculationDao,
                    DateCalculatorImpl()
                ) as T
            }
        }
    }
}