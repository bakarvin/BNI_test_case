package com.example.bnitestcase.data.remote.api


import retrofit2.Response
import retrofit2.http.GET

interface PromoApi {
    companion object {
        const val BASE_URL = "https://content.digi46.id/"
    }
    @GET(BASE_URL+"promos")
    suspend fun getPromoBanner(): Response<Any>
}