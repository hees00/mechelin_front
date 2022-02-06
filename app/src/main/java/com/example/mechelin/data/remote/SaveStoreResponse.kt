package com.example.mechelin.data.remote

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor
import java.util.*


data class SaveStoreResponse(
    @SerializedName("isSuccess") val isSuccess:Boolean,
    @SerializedName("code") val code :Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: Object
    )

data class Result(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("storeIdx") val storeIdx: Int
)
