package com.example.booklisting

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

val gson: Gson = GsonBuilder().setLenient().create()
var baseUrl: String = "https://www.googleapis.com/books/v1/"
val retrofit: Retrofit =
    Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson))
        .build()

interface QueryInterface {
    @GET
    fun getBooks(@Url Url: String): Call<Book>
}