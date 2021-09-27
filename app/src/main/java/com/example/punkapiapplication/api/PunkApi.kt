package com.example.punkapiapplication.api

import com.example.punkapiapplication.data.PunkData
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApi {
    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }

    @GET("beers")
    suspend fun fetchData(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<PunkData>
}