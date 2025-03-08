package com.example.mui1.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatToText(locale : Locale) : String{
    val formatter = SimpleDateFormat("dd MMMM yyyy", locale)
    return formatter.format(date)
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}