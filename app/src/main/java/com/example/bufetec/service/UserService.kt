package com.example.navtemplate.service

import com.example.bufetec.data.BibliotecaUserResponse
import com.example.bufetec.viewmodel.LibraryItem
import com.example.navtemplate.data.LoginUserRequest
import com.example.navtemplate.data.LoginUserResponse
import com.example.navtemplate.data.RegisterUserRequest
import com.example.navtemplate.data.RegisterUserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface UserService {
    companion object {
        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        // Aumentamos los tiempos de espera
        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)  // Tiempo de espera de conexión
            .readTimeout(30, TimeUnit.SECONDS)     // Tiempo de espera para leer
            .writeTimeout(30, TimeUnit.SECONDS)    // Tiempo de espera para escribir
            .build()

        val instance: UserService = Retrofit.Builder()
            .baseUrl("https://bufetecapi-3fe3.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UserService::class.java)
    }

    @POST("api/users/register")
    suspend fun addUser(@Body user: RegisterUserRequest): Response<RegisterUserResponse>  // Cambiamos el tipo de retorno

    @POST("api/users/login")
    suspend fun loginUser(@Body user: LoginUserRequest): Response<LoginUserResponse>  // Cambiamos el tipo de retorno

    @GET("api/users/biblioteca")
    suspend fun getLibraryItems(): Response<BibliotecaUserResponse>
}
