package com.example.mechelin.ui.detail

import com.example.mechelin.ui.main.review.ReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("/reviews/{userIdx}/{storeIdx}")
    fun getReviews(
        @Header("X-ACCESS-TOKEN") jwtToken: String,
        @Path("userIdx") userIdx: Int,
        @Path("storeIdx") storeIdx: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ) : Call<PostResult>
}