package com.example.mechelin.ui.main

import com.example.mechelin.ui.main.review.ReviewResponse
import com.example.mechelin.ui.main.search.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("/reviews/15")
    fun getReview(): Call<ReviewResponse>

    @GET("/search/15")
    fun getSearchStoreResult(@Query("keyword") keyword: String): Call<SearchResponse>

    @GET("/search/15")
    fun getSearchHashtagResult(@Query("keyword") keyword: String): Call<SearchResponse>

    @GET("/stores/15/8")
    fun getStore(@Query("starRating") starRating: String,
                @Query("deliveryService") deliveryService: String,
                @Query("page") page: Int,
                @Query("pageSize") pageSize: Int): Call<StoreResponse>
}