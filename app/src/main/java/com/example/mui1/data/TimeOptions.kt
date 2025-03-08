package com.example.mui1.data

sealed class TimeOptions(val description : String){
    object Minutes : TimeOptions("Minutes")
    object Hours : TimeOptions("Hours")
    object Days : TimeOptions("Days")
    object Weeks : TimeOptions("Weeks")
    object Months : TimeOptions("Months")
    object Years : TimeOptions("Years")
}