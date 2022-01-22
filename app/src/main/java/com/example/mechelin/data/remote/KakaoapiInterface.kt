package com.example.mechelin.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoapiInterface {
    @GET("v2/local/search/keyword.json")
    fun getSearchKeyword(
        @Header("Authorization") key: String,  //카카오 API 인증키
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
//        @Query("category_group_code") category_group_code: String, //FD6-음식점 CE7-카페
    ): Call<Researchkeyword>
}