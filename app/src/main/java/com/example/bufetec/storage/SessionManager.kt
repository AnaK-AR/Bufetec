package com.example.bufetec.storage

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        const val KEY_JWT_TOKEN = "jwt_token"
    }

    // Función para guardar el JWT
    fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_JWT_TOKEN, token)
        editor.apply()
    }

    // Función para obtener el JWT
    fun fetchAuthToken(): String? {
        return sharedPreferences.getString(KEY_JWT_TOKEN, null)
    }

    // Función para eliminar el JWT (por ejemplo, al cerrar sesión)
    fun clearAuthToken() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_JWT_TOKEN)
        editor.apply()
    }
}
