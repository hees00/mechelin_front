package com.example.mechelin.ui.main

import com.example.mechelin.ui.main.review.ReviewResponse
import com.example.mechelin.ui.main.search.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("{fullUrl}")
    fun getReview(@Path("fullUrl", encoded = true) fullUrl: String,
                  @Header("X-ACCESS-TOKEN") token: String?): Call<ReviewResponse>

    @GET("{fullUrl}")
    fun getSearchStoreResult(
        @Path("fullUrl", encoded = true) fullUrl: String,
        @Header("X-ACCESS-TOKEN") token: String?,
        @Query("keyword") keyword: String): Call<SearchResponse>

    @GET("{fullUrl}")
    fun getSearchHashtagResult(
        @Path("fullUrl", encoded = true) fullUrl: String,
        @Header("X-ACCESS-TOKEN") token: String?,
        @Query("keyword") keyword: String): Call<SearchResponse>

    @GET("{fullUrl}")
    fun getStore(
        @Path("fullUrl", encoded = true) fullUrl: String,
        @Header("X-ACCESS-TOKEN") token: String?,
        @Query("starRating") starRating: String,
        @Query("deliveryService") deliveryService: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int): Call<StoreResponse>
}