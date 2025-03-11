package com.example.mui1.presentation

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
        Text(text = Date(currentSelectedTimeMillis).formatToText(Locale.getDefault()))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = dateShiftInput.toString(),
                onValueChange = { value ->
                    dateShiftInput = value.toDouble()
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.width(8.dp))
            DropdownMenu {
                timeOption = it
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
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
        Spacer(Modifier.height(16.dp))
        DatePickerDocked()
    }
}
