package com.example.mechelin.ui.main

import android.os.FileObserver.ACCESS
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface ReviewInterface {
    @GET("/reviews/1")
    fun getReview(): Call<ReviewResponse>
}