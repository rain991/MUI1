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
import com.example.mui1.data.formatToText
import java.util.Date
import java.util.Locale

@Composable
fun CalendarSelectorScreenComponent(
    paddingValues: PaddingValues,
    viewModel: CalendarCalculatorViewModel
) {
    var dateShiftInput by remember { mutableDoubleStateOf(10.0) }
    var timeOption: TimeOptions by remember { mutableStateOf(TimeOptions.Hours) }
    var currentSelectedTimeMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var resultMillis: Long? by remember { mutableStateOf(null) }
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
            text = Date(currentSelectedTimeMillis).formatToText(Locale.getDefault()),
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
                value = dateShiftInput.toString(),
                onValueChange = { value ->
                    dateShiftInput = value.toDouble()
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.headlineSmall
            )
            DropdownMenu(currentTimeOption = timeOption) {
                timeOption = it
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
                resultMillis = viewModel.calculateDate(
                    inputDate = Date(currentSelectedTimeMillis),
                    timeShift = dateShiftInput,
                    direction = com.example.mui1.data.TimeShiftDirection.PAST,
                    timeOptions = timeOption
                ).time
            }) {
                Text(text = "Find history date")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                resultMillis = viewModel.calculateDate(
                    inputDate = Date(currentSelectedTimeMillis),
                    timeShift = dateShiftInput,
                    direction = com.example.mui1.data.TimeShiftDirection.FUTURE,
                    timeOptions = timeOption
                ).time
            }) {
                Text(text = "Find future date")
            }
        }
        AnimatedVisibility(resultMillis != null) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Result date", style = MaterialTheme.typography.headlineMedium)
                Text(
                    text = Date(resultMillis!!).formatToText(Locale.getDefault()),
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    currentSelectedTimeMillis = resultMillis!!
                    resultMillis = null
                }) {
                    Text(text = "Set as current date", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
