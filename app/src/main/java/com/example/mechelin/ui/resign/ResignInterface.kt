package com.example.mechelin.ui.resign

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ResignInterface {
    @PATCH("/users/{userIdx}/status")
    fun resign(
        @Path("userIdx") userIdx:Int,
        @Body password : String
    ): Call<ResignResponse>
}