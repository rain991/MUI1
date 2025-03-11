package com.example.mui1.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mui1.R
import com.example.mui1.data.TimeOptions
import com.example.mui1.data.TimeShiftDirection
import com.example.mui1.data.formatToText
import java.util.Date
import java.util.Locale

/**
 * Used to display single completed date calculation
 *
 * @param inputDate
 * @param calculatedDate
 * @param timeShift
 * @param timeOptions
 * @param direction
 */
@Composable
fun DateCalculationComposable(inputDate : Date, calculatedDate : Date, timeShift : Double, timeOptions: TimeOptions, direction : TimeShiftDirection) {
    val locale = Locale.getDefault()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = inputDate.formatToText(locale))

            Column(verticalArrangement = Arrangement.SpaceAround) {
                Image(
                    painter = painterResource(id = if (direction == TimeShiftDirection.FUTURE) R.drawable.clockwise_time else R.drawable.counterclockwise_time),
                    contentDescription = null
                )
            }

        }
    }
}

@Preview
@Composable
private fun prev() {
    DateCalculationComposable(inputDate = Date(), calculatedDate = Date(), timeShift = 10.0, timeOptions = TimeOptions.Days, direction = TimeShiftDirection.FUTURE)
}


