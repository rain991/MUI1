package com.example.mui1

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatToText(locale : Locale) : String{
    val formatter = SimpleDateFormat("dd MMMM yyyy", locale)
    return formatter.format(date)
}