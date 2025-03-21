package com.example.mui1.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mui1.R
import com.example.mui1.data.TimeOptions
import com.example.mui1.data.TimeShiftDirection
import com.example.mui1.data.formatDateWithYear
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
fun DateCalculationComposable(
    inputDate: Date,
    calculatedDate: Date,
    timeShift: Double,
    timeOptions: TimeOptions,
    direction: TimeShiftDirection
) {
    val locale = Locale.getDefault()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = inputDate.formatDateWithYear(), textAlign = TextAlign.Center)
            Spacer(Modifier.width(4.dp))
            Column(verticalArrangement = Arrangement.SpaceAround) {
                Image(
                    painter = painterResource(id = if (direction == TimeShiftDirection.FUTURE) R.drawable.clockwise_time else R.drawable.counterclockwise_time),
                    contentDescription = null,
                    modifier = Modifier.height(40.dp)
                )
            }
            Spacer(Modifier.width(4.dp))
            Text(text = calculatedDate.formatDateWithYear(), textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
private fun prev() {
    DateCalculationComposable(
        inputDate = Date(),
        calculatedDate = Date(),
        timeShift = 10.0,
        timeOptions = TimeOptions.Days,
        direction = TimeShiftDirection.FUTURE
    )
}


