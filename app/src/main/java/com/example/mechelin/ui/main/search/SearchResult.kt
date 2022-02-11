package com.example.mechelin.ui.main.search

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("store") var store : ArrayList<Store>,
    @SerializedName("hashtag") var hashtag: ArrayList<Hashtag>
)
