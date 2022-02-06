package com.example.mechelin.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface SaveStoreService {

    @Headers("accept: application/json", "content-type: application/json")
    @POST("/stores")
    fun saveStore(
        @Body postStoreReq: JSONObject
//                  @Part imageFile : MultipartBody.Part
    ):Call<SaveStoreResponse>
}


//interface SaveStoreService {
//    @Multipart
//    @POST("/stores")
//    fun saveStore(@Part storeName: RequestBody,
//                  @Part imageFile : MultipartBody.Part
//    ):Call<SaveStoreResponse>
//}