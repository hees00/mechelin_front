package com.example.mechelin.ui.main

import android.os.FileObserver.ACCESS
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface ReviewInterface {
    @GET("/reviews/main/")
    // @Headers("x-access-token:JWT eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NDI1MjQ3NDgsImV4cCI6MTY0Mzk5NTk3N30.B6vyg-qnjJ_7bhsyXNAfrxAFcYyYUpn3CQobpEL8yTc")
    fun getReview(): Call<ReviewResponse>
}