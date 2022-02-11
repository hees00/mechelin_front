package com.example.mechelin.ui.main.search

import com.google.gson.annotations.SerializedName

data class Hashtag(
    @SerializedName("tagIdx") val tagIdx: Int = 0,
    @SerializedName("tagName") val tagName: String = "",
    @SerializedName("count") val count: Int = 0
)
