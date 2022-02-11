package com.example.mechelin.ui.main.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: SearchResult
)
