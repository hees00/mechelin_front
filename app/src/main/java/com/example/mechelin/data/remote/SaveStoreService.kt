package com.example.mechelin.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface SaveStoreService {
    @GET("/stores")
    fun saveStore(@Body store: Store):Call<SaveStoreResponse>
}