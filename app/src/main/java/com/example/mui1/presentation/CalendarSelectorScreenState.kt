package com.example.mui1.presentation

import com.example.mui1.data.DateCalculation
import com.example.mui1.data.TimeOptions
import com.example.mui1.data.TimeShiftDirection
import java.util.Date

data class CalendarSelectorScreenState(
    val selectedDate : Date,
    val resultDate : Date?,
    val timeShift : Double,
    val timeOptions : TimeOptions,
    val dateCalculations : List<DateCalculation>,
    val error : CalendarSelectorScreenErrors?
)
