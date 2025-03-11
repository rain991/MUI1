package com.example.mui1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.mui1.data.CalendarCalculatorViewModel
import com.example.mui1.data.TimeOptions
import com.example.mui1.presentation.ui.theme.MUI1Theme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calendarCalculatorViewModel: CalendarCalculatorViewModel by viewModels { CalendarCalculatorViewModel.Factory }
        setContent {
            MUI1Theme {
                CalendarSelectorScreen(
                    viewModel = calendarCalculatorViewModel
                )
            }
        }
    }
}
