package com.example.bufetec.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

@Composable
fun DatePicker(
    selectedDate: ZonedDateTime,
    onDateChange: (ZonedDateTime) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.time = Date.from(selectedDate.toInstant())

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                val newDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
                val zonedDate = newDate.atStartOfDay(ZoneId.systemDefault())
                onDateChange(zonedDate)
                showDialog = false
            },
            year,
            month,
            day
        ).show()
    }

    Button(onClick = { showDialog = true }) {
        Text(text = "Select Date: ${selectedDate.toLocalDate()}")
    }
}