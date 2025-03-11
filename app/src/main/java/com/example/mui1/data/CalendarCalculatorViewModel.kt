package com.example.mui1.data

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import java.util.Date
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mui1.presentation.CalendarSelectorScreenErrors
import com.example.mui1.presentation.CalendarSelectorScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.logging.Logger

class CalendarCalculatorViewModel(private val dateCalculationDao: DateCalculationDAO, private val dateCalculator: DateCalculator) : ViewModel() {
    private val _calendarCalculatorScreenState = MutableStateFlow(
        CalendarSelectorScreenState(
            selectedDate = Date(),
            resultDate = null,
            timeShift = 10.0,
            timeOptions = TimeOptions.Hours,
            dateCalculations = emptyList(),
            error = null
        )
    )
    val calendarCalculatorScreenState = _calendarCalculatorScreenState.asStateFlow()

    init {
        _calendarCalculatorScreenState.update { it.copy(selectedDate = Date(System.currentTimeMillis())) }
        viewModelScope.launch {
            dateCalculationDao.getAll().collect { dateCalculations ->
                _calendarCalculatorScreenState.update { it.copy(dateCalculations = dateCalculations) }
            }
        }
    }

    suspend fun addDateCalculation(dateCalculation: DateCalculation) {
        dateCalculationDao.insertItem(dateCalculation)
    }

    fun getCalculationHistory(): Flow<List<DateCalculation>> {
        return dateCalculationDao.getAll()
    }

    fun calculateDate(direction : TimeShiftDirection) {
        clearError()
        try{
            val calculatedDate = dateCalculator.calculateDate(inputDate = _calendarCalculatorScreenState.value.selectedDate, timeShift = _calendarCalculatorScreenState.value.timeShift, direction = direction, timeOptions = _calendarCalculatorScreenState.value.timeOptions)
            Log.d(CalendarCalculatorViewModel::class.java.simpleName, calculatedDate.time.toString())
            viewModelScope.launch {
                addDateCalculation(DateCalculation(InputDate =  _calendarCalculatorScreenState.value.selectedDate.time, CalculatedDate = calculatedDate.time, TimeOption = _calendarCalculatorScreenState.value.timeOptions.description))
            }
            _calendarCalculatorScreenState.update { it.copy(resultDate = calculatedDate) }
        }catch (e : Exception){
            _calendarCalculatorScreenState.update { it.copy(error = CalendarSelectorScreenErrors.IncorrectDate) }
            _calendarCalculatorScreenState.update { it.copy(resultDate = null) }
        }
    }

    fun setSelectedDate(selectedDate: Date) {
        _calendarCalculatorScreenState.update { it.copy(selectedDate = selectedDate) }
    }

    fun setTimeShift(timeShift: Double) {
        _calendarCalculatorScreenState.update { it.copy(timeShift = timeShift) }
    }

    fun setTimeOptions(timeOptions: TimeOptions) {
        _calendarCalculatorScreenState.update { it.copy(timeOptions = timeOptions) }
    }

    fun setResultDateAsCurrentSelected(){
        setSelectedDate(_calendarCalculatorScreenState.value.resultDate!!)
        _calendarCalculatorScreenState.update { it.copy(resultDate = null) }
    }

    private fun clearError() {
        _calendarCalculatorScreenState.update { it.copy(error = null) }
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