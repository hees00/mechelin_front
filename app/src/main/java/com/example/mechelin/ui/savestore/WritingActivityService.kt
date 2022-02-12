package com.example.mechelin.ui.savestore

import android.util.Log
import com.example.mechelin.data.remote.SaveStoreResponse
import com.example.mechelin.data.remote.Store
import com.example.mechelin.ui.main.ApiClient
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

//MultipartBody.Part?
class WritingActivityService (val view : WritingActivityView) {

    fun tryWriting(postStoreReq: Store,image: ArrayList<MultipartBody.Part?>) {
        val signInterface = ApiClient.getRetrofit().create(SaveStoreService::class.java)
        signInterface.saveStore(postStoreReq,image).enqueue(object :
            Callback<SaveStoreResponse> {
            override fun onResponse(call: Call<SaveStoreResponse>, response: Response<SaveStoreResponse>) {
                Log.d("REQUEST-SUCCESS", "onresponse 들어옴")
                Log.d("REQUEST-SUCCESS", response.toString())
                view.onPostWritingSuccess(response.body() as SaveStoreResponse)
            }

            override fun onFailure(call: Call<SaveStoreResponse>, t: Throwable) {
                view.onPostWritingFailure(t.message ?: "통신 오류")
            }
        })
    }



}