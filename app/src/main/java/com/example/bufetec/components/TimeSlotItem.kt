package com.example.bufetec.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.bufetec.model.TimeSlot
import java.time.format.DateTimeFormatter

@Composable
fun TimeSlotItem(timeSlot: TimeSlot) {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")

    var isClicked by remember { mutableStateOf(false) }

    Button(
        onClick = {
            if (!isClicked) {
                // Handle time slot selection
                // For example, navigate to a booking confirmation screen or mark as selected
                isClicked = true
            }
        },
        enabled = !isClicked,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "${timeSlot.startTime.format(formatter)} - ${timeSlot.endTime.format(formatter)}"
        )
    }
}