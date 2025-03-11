package com.example.mui1.data

sealed class TimeOptions(val description : String){
    object Minutes : TimeOptions("Minutes")
    object Hours : TimeOptions("Hours")
    object Days : TimeOptions("Days")
    object Weeks : TimeOptions("Weeks")
    object Months : TimeOptions("Months")
    object Years : TimeOptions("Years")

    companion object {
        fun parseStringToTimeOption(string : String) : TimeOptions{
            return when(string){
                "Minutes" -> Minutes
                "Hours" -> Hours
                "Days" -> Days
                "Weeks" -> Weeks
                "Months" -> Months
                "Years" -> Years
                else -> {throw IllegalArgumentException("Unknown time option during parsing string to time option: $string") }
            }
        }
    }
}