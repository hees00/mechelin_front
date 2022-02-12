package com.example.mechelin.ui.savestore

import com.example.mechelin.data.remote.SaveStoreResponse
import com.example.mechelin.data.remote.Store
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SaveStoreService {
    @Multipart
    @POST("/stores")
    fun saveStore(
        @Part("postStoreReq") postStoreReq : Store,
        @Part imageFile: ArrayList<MultipartBody.Part?>
    ):Call<SaveStoreResponse>
}

//@Part imageFile : MultipartBody.Part

//    @Headers("accept: application/json", "content-type: application/json")
//                  @Part imageFile : MultipartBody.Part

//interface SaveStoreService {
//    @Multipart
//    @POST("/stores")
//    fun saveStore(@Part storeName: RequestBody,
//                  @Part imageFile : MultipartBody.Part
//    ):Call<SaveStoreResponse>
//}