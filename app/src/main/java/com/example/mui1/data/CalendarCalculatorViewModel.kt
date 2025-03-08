package com.example.mui1.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import java.util.Date
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras

class CalendarCalculatorViewModel(private val dateCalculationDao: DateCalculationDAO) :
    ViewModel() {
    suspend fun addDateCalculation(dateCalculation: DateCalculation) {
        dateCalculationDao.insertItem(dateCalculation)
    }

    fun getCalculationHistory(): Flow<List<DateCalculation>> {
        return dateCalculationDao.getAll()
    }

    fun calculateDate(
        inputDate: Date,
        timeShift: Double,
        direction: TimeShiftDirection,
        timeOptions: TimeOptions
    ): Date {
        val inputDateMillis = inputDate.time
        val millisShift = when (timeOptions) {
            TimeOptions.Days -> {
                timeShift * 86_400_000L
            }

            TimeOptions.Hours -> {
                timeShift * 3_600_000L
            }

            TimeOptions.Minutes -> {
                timeShift * 60_000L
            }

            TimeOptions.Months -> {
                timeShift * 2_630_016_000L
            }

            TimeOptions.Weeks -> {
                timeShift * 604_800_000L
            }

            TimeOptions.Years -> {
                timeShift * 31_557_600_000L
            }
        }
        return when (direction) {
            TimeShiftDirection.PAST -> {
                Date(inputDateMillis - millisShift.toLong())
            }

            TimeShiftDirection.FUTURE -> {
                Date(inputDateMillis + millisShift.toLong())
            }
        }
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
                    dateCalculationDao
                ) as T
            }
        }
    }
}