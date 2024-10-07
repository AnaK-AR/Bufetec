package com.example.navtemplate.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.navtemplate.data.LoginUserRequest
import com.example.navtemplate.data.LoginUserResponse
import com.example.navtemplate.data.RegisterUserRequest
import com.example.navtemplate.data.RegisterUserResponse
import com.example.navtemplate.service.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class UserViewModel(private val userService: UserService) : ViewModel() {

    var user_email by mutableStateOf("pegomezp@gmail.com")
    var password by mutableStateOf("1234")
    var user_firstname by mutableStateOf("")
    var user_lastname by mutableStateOf("")
    var user_username by mutableStateOf("")

    var isUserLogged by mutableStateOf(false)

    private val _login = MutableStateFlow<LoginUserState>(LoginUserState.Initial)
    val login: StateFlow<LoginUserState> = _login

    private val _register = MutableStateFlow<RegisterUserState>(RegisterUserState.Initial)
    val register: StateFlow<RegisterUserState> = _register


    fun registerUser(user: RegisterUserRequest) {
        viewModelScope.launch {
            _register.value = RegisterUserState.Initial

            try {
                _register.value = RegisterUserState.Loading
                val response = userService.addUser(user)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _register.value = RegisterUserState.Success(it)
                    } ?: run {
                        throw Exception("Error: Response body is null")
                    }
                } else {
                    throw Exception("Error: Unexpected response code ${response.code()}")
                }
            } catch (e: Exception) {
                _register.value = RegisterUserState.Error(e.message.toString())
            }
        }
    }



    fun loginUser(user: LoginUserRequest) {
        _login.value = LoginUserState.Initial
        viewModelScope.launch {
            try {
                _login.value = LoginUserState.Loading
                val response = userService.loginUser(user)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _login.value = LoginUserState.Success(it)
                    } ?: run {
                        throw Exception("Error: Response body is null")
                    }
                } else {
                    throw Exception("Error: Unexpected response code ${response.code()}")
                }
            } catch (e: Exception) {
                _login.value = LoginUserState.Error("Hubo un error: " + e.message.toString())
            }
        }
    }


}


sealed class LoginUserState {
    object Initial : LoginUserState()
    object Loading : LoginUserState()
    data class Success(val loginResponse: LoginUserResponse) : LoginUserState()
    data class Error(val errorMessage: String) : LoginUserState()
}

sealed class RegisterUserState {
    object Initial : RegisterUserState()
    object Loading : RegisterUserState()
    data class Success(val registerResponse: RegisterUserResponse) : RegisterUserState()
    data class Error(val errorMessage: String) : RegisterUserState()
}