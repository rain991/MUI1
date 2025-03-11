package com.example.mui1.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.mui1.data.CalendarCalculatorViewModel

@Composable
fun CalendarSelectorScreen(
    viewModel : CalendarCalculatorViewModel
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Row(modifier = Modifier.fillMaxWidth().padding(paddingValues), horizontalArrangement = Arrangement.Center){
            Text(text = "Date Calculator", style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold))
        }
        CalendarSelectorScreenComponent(paddingValues = paddingValues, viewModel = viewModel)
    }
}