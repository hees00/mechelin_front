package com.example.mechelin.ui.signup

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignupService {
    @POST("/users/")
    fun requestSignup(
        @Body signupData: SignupData
    ) : Call<SignupResult>
}