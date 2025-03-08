package com.example.mui1.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.mui1.data.CalendarCalculatorViewModel

@Composable
fun CalendarSelectorScreen(
    viewModel : CalendarCalculatorViewModel
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(text = "Date Calculator", style = TextStyle(fontWeight = FontWeight.SemiBold))
        }
        CalendarSelectorScreenComponent(paddingValues)
    }
}