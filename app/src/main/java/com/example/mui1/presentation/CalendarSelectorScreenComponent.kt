package com.example.mui1.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mui1.data.CalendarCalculatorViewModel
import com.example.mui1.data.TimeOptions
import com.example.mui1.data.TimeShiftDirection
import com.example.mui1.data.formatToText
import java.util.Date
import java.util.Locale

@Composable
fun CalendarSelectorScreenComponent(
    paddingValues: PaddingValues,
    viewModel: CalendarCalculatorViewModel
) {
    val screenState = viewModel.calendarCalculatorScreenState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selected date", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = screenState.value.selectedDate.formatToText(Locale.getDefault()),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.4f))
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Time shift", style = MaterialTheme.typography.headlineMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = screenState.value.timeShift.toString(),
                onValueChange = { value ->
                    viewModel.setTimeShift(value.toDouble())
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.headlineSmall
            )
            DropdownMenu(currentTimeOption = screenState.value.timeOptions) {
                viewModel.setTimeOptions(it)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Buttons to trigger time calculation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                viewModel.calculateDate(direction = TimeShiftDirection.PAST)
            }) {
                Text(text = "Calculate past date")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                viewModel.calculateDate(direction = TimeShiftDirection.FUTURE)
            }) {
                Text(text = "Calculate future date")
            }
        }
        AnimatedVisibility(screenState.value.resultDate != null) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Result date", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = screenState.value.resultDate!!.formatToText(Locale.getDefault()),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    viewModel.setResultDateAsCurrentSelected()
                }) {
                    Text(text = "Set as current date", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
