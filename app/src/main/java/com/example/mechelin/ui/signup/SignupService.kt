package com.example.mechelin.ui.signup

import retrofit2.Call
import retrofit2.http.*

interface SignupService {
    @POST("/users/")
    fun requestSignup(
        @Body signupData: SignupData
    ): Call<SignupResult>

    @POST("/auth/phonenumber")
    fun authPhone(
        @Body recipientPhoneNumber: String
    ): Call<PhoneResult>

    @GET("/auth/")
    fun confirmNum(
        @Query("phoneNumber") phoneNumber: String,
        @Query("certNumber") certNumber: String
    ): Call<ConfrimNumResult>
}
