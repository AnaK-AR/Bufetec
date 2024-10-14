package com.example.bufetec.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bufetec.data.CalendlyRepository
import com.example.bufetec.model.TimeSlot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class CitasViewModel : ViewModel() {

    private val repository = CalendlyRepository()

    private val _timeSlots = MutableStateFlow<List<TimeSlot>>(emptyList())
    val timeSlots: StateFlow<List<TimeSlot>> = _timeSlots

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchAvailableTimeSlots(selectedDate: LocalDate) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch {
            try {
                val slots = repository.getAvailableTimeSlots(selectedDate)
                _timeSlots.value = slots
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Failed to load time slots."
            } finally {
                _isLoading.value = false
            }
        }
    }
}