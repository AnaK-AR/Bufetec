package com.example.navtemplate.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class UserViewModel(application: Application, private val userService: UserService) : AndroidViewModel(application) {

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
                        val sharedPreferences =
                            getApplication<Application>()
                                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

                        sharedPreferences.edit().putString("jwt_token", it.token).apply()
                        isUserLogged = true
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
                val a = user
                _login.value = LoginUserState.Loading

                val response = userService.loginUser(user)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val sharedPreferences =
                            getApplication<Application>()
                                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

                        sharedPreferences.edit().putString("jwt_token", it.token).apply()
                        isUserLogged = true
                        _login.value = LoginUserState.Success(it)
                    } ?: run {
                        throw Exception("Error: Response body is null")
                    }
                } else {
                    throw Exception("${response.code()}")
                }
            } catch (e: Exception) {
                _login.value = LoginUserState.Error("Hubo un error: " + e.message.toString())
            }
        }
    }

    // Función para verificar si el usuario tiene la sesión activa
    // al iniciar la aplicación
    fun startUser() {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwt_token", null)
        if (token != null) {
            isUserLogged = true
        }
    }
}

class UserViewModelFactory(
    private val application: Application,
    private val userService: UserService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(application, userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
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