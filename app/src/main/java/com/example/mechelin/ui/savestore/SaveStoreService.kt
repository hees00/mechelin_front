package com.example.mechelin.ui.savestore

import com.example.mechelin.data.remote.SaveStoreResponse
import com.example.mechelin.data.remote.postStoreReq
import retrofit2.Call
import retrofit2.http.*

interface SaveStoreService {
    @POST("/stores")
    fun saveStore(
        @Body postStoreReq: postStoreReq
    ):Call<SaveStoreResponse>
}

//    @Headers("accept: application/json", "content-type: application/json")
//                  @Part imageFile : MultipartBody.Part

//interface SaveStoreService {
//    @Multipart
//    @POST("/stores")
//    fun saveStore(@Part storeName: RequestBody,
//                  @Part imageFile : MultipartBody.Part
//    ):Call<SaveStoreResponse>
//}