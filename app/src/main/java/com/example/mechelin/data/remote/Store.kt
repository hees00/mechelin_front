package com.example.mechelin.data.remote

data class Store(
    var userIdx:Int,
    var storeName:String,
    var address: String,
    var tel: String,
    var x: Double,
    var y: Double,
    var starRate: Double,
    var contents: String,
    var tagName: ArrayList<String>,
    var categoryIdx:Int,
    var deliveryService: String
)

data class Review(
    var starRate: Double,
    var contents: String,
    var tagName: List<String>
)