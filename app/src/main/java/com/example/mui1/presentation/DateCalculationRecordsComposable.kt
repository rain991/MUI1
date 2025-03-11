package com.example.mui1.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mui1.data.DateCalculation
import com.example.mui1.data.TimeOptions
import com.example.mui1.data.TimeShiftDirection
import java.util.Date
import kotlin.math.absoluteValue

@Composable
fun DateCalculationRecordsComposable(dateCalculations: List<DateCalculation>) {
    val listState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.8f))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Previous date calculations", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(state = listState, modifier = Modifier.fillMaxWidth()){
            items(count = dateCalculations.size){index ->
                val currentDateCalculation = dateCalculations[index]
                val timeShift = (currentDateCalculation.CalculatedDate - currentDateCalculation.InputDate).toDouble()
                DateCalculationComposable(inputDate = Date(currentDateCalculation.InputDate), calculatedDate = Date(currentDateCalculation.CalculatedDate), timeShift = timeShift.absoluteValue, timeOptions = TimeOptions.parseStringToTimeOption(currentDateCalculation.TimeOption), direction = if(timeShift > 0) TimeShiftDirection.FUTURE else TimeShiftDirection.PAST)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}