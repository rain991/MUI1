package com.example.mui1.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mui1.data.TimeOptions

@Composable
fun DropdownMenu(currentTimeOption : TimeOptions, selectedTimeOptions: (TimeOptions) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val timeOptions = listOf(TimeOptions.Minutes, TimeOptions.Hours, TimeOptions.Days, TimeOptions.Weeks, TimeOptions.Months, TimeOptions.Years)
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = currentTimeOption.description, modifier = Modifier.clickable{ expanded = !expanded })
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            repeat(timeOptions.size) {
                DropdownMenuItem(
                    text = { Text(text = timeOptions[it].description) },
                    onClick = {
                        when (timeOptions[it]) {
                            TimeOptions.Days -> selectedTimeOptions(TimeOptions.Days)
                            TimeOptions.Hours -> selectedTimeOptions(TimeOptions.Hours)
                            TimeOptions.Minutes -> selectedTimeOptions(TimeOptions.Minutes)
                            TimeOptions.Months -> selectedTimeOptions(TimeOptions.Months)
                            TimeOptions.Weeks -> selectedTimeOptions(TimeOptions.Weeks)
                            TimeOptions.Years -> selectedTimeOptions(TimeOptions.Years)
                        }
                    }
                )
            }
        }
    }
}