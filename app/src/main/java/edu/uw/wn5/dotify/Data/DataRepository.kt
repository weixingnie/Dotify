package edu.uw.wn5.dotify.Data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DataRepository{
    private val userJason = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataService::class.java)

    suspend fun fetchUser(): User = userJason.fetchUser()
}

interface DataService {
    @GET("echeeUW/codesnippets/master/user_info.json")
    suspend fun fetchUser(): User
}