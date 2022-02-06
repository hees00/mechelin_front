package com.example.mechelin.ui.signup

import com.google.gson.annotations.SerializedName

data class SignupResult(
    @SerializedName("result") val result: JwtResult,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class JwtResult(
    @SerializedName("result") val userIdx:Int,
    @SerializedName("jwt") val jwt:String
)
