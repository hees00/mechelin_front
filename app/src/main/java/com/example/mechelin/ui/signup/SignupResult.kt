package com.example.mechelin.ui.signup

import com.google.gson.annotations.SerializedName

data class SignupResult(
    @SerializedName("result") val result: JwtResult,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class JwtResult(
    @SerializedName("userIdx") val userIdx:Int,
    @SerializedName("jwt") val jwt:String
)

data class PhoneResult(
    @SerializedName("result") val result : Result,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class Result(
    @SerializedName("requestId") val requestId:String,
    @SerializedName("requestTime") val requestTime:String,
    @SerializedName("statusCode") val statusCode:String,
    @SerializedName("statusName") val statusName:String
)
