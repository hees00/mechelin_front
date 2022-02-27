package com.example.mechelin.ui.detail

import com.example.mechelin.ui.main.review.ReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("/reviews/{userIdx}/{storeIdx}")
    fun getReviews(
        @Header("X-ACCESS-TOKEN") jwtToken: String,
        @Path("userIdx") userIdx: Int,
        @Path("storeIdx") storeIdx: Int,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ) : Call<PostResult>

    @PATCH("/reviews/{userIdx}/{reviewIdx}/status")
    fun DeleteReview(
        @Path("userIdx") userIdx: Int,
        @Path("reviewIdx") reviewIdx: Int
    ):Call<deleteresponse>

    @PATCH("/stores/{userIdx}/{storeIdx}/status")
    fun DeleteStore(
        @Path("userIdx") userIdx:Int,
        @Path("storeIdx") storeIdx:Int,
    ):Call<deletestoreresponse>

}