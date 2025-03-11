package com.example.mui1.presentation

sealed class CalendarSelectorScreenErrors(val message : String) {
    object IncorrectDate : CalendarSelectorScreenErrors("Inappropriate date")
}