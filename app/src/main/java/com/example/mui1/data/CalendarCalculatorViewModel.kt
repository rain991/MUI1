package com.example.mui1.data

import androidx.lifecycle.ViewModel
import java.util.Date

class CalendarCalculatorViewModel : ViewModel() {


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
}