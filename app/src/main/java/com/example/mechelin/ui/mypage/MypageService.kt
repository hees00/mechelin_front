package com.example.mechelin.ui.mypage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MypageService {
    @GET("/users/7")
    fun getmypageInfo(
    ): Call<MypageResponse>
}