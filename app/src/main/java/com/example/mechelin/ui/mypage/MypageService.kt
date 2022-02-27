package com.example.mechelin.ui.mypage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MypageService {
    @GET("{fullUrl}")
    fun getmypageInfo(
        @Path("fullUrl", encoded = true) fullUrl: String,
        @Header("X-ACCESS-TOKEN") token: String?,
    ): Call<MypageResponse>
}