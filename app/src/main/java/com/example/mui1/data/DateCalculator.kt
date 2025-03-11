package com.example.mui1.data

import java.util.Date

interface DateCalculator {
    fun calculateDate(
        inputDate: Date,
        timeShift: Double,
        direction: TimeShiftDirection,
        timeOptions: TimeOptions
    ): Date
}