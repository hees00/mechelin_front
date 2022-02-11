package com.example.mechelin.ui.resign

import com.google.gson.annotations.SerializedName

data class ResignResponse(
    @SerializedName("result") val result : Result,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String
)

data class Result(
    @SerializedName("userIdx") val userIdx: Int

)
