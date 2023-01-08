package com.example.enliven.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StreamTokenApi {
    @GET("token")
    suspend fun getToken(
        @Query("user_id") userId: String
    ) : TokenResponse

    companion object{
        operator fun invoke(): StreamTokenApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://enliven-app-server.onrender.com/")
                .build()
                .create(StreamTokenApi::class.java)
        }
    }
}