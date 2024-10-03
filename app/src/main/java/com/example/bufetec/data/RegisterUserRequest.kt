package com.example.navtemplate.data

data class RegisterUserRequest(
    val user_email: String,
    val password: String,
    val user_firstname: String,
    val user_lastname: String,
    val user_username: String,

)