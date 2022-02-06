package com.example.mechelin.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SaveStoreService {
    @Multipart
    @POST("/stores")
    fun saveStore(@Part("postStoreReq") postStoreReq: RequestBody,
                  @Part imageFile : MultipartBody.Part
    ):Call<SaveStoreResponse>
}


//interface SaveStoreService {
//    @Multipart
//    @POST("/stores")
//    fun saveStore(@Part storeName: RequestBody,
//                  @Part imageFile : MultipartBody.Part
//    ):Call<SaveStoreResponse>
//}