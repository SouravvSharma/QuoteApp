package com.example.quote_app.ApiService

import com.example.quote_app.Model.Quotes
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {

    @GET("/random")
    suspend fun getQuotes(): Response<Quotes>
}