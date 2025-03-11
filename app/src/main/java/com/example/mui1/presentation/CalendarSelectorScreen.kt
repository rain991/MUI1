package com.example.mui1.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mui1.data.CalendarCalculatorViewModel

@Composable
fun CalendarSelectorScreen(
    viewModel : CalendarCalculatorViewModel
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
       Column(modifier = Modifier.fillMaxSize().padding(paddingValues)){
           Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
               Text(text = "Date Calculator", style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold))
           }
           Spacer(Modifier.height(60.dp))
           CalendarSelectorScreenComponent(viewModel = viewModel)
       }
    }
}