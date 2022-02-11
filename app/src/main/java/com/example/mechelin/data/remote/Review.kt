package com.example.mechelin.data.remote


data class Review(
    var starRate: Double,
    var contents: String,
    var tagName: List<String>
)