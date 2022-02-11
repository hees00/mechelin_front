package com.example.mechelin.ui.mypage

import com.google.gson.annotations.SerializedName

data class MypageResponse(
    @SerializedName("result") val result : Result,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String
)

data class Result(
    @SerializedName("nickName") val nickName : String,
    @SerializedName("email") val email: String,
    @SerializedName("storeCnt") val storeCnt : Int,
    @SerializedName("reviewCnt") val reviewCnt: Int

)
