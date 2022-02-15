package com.example.mechelin.ui.mypage

import android.util.Log
import com.example.mechelin.data.remote.SaveStoreResponse
import com.example.mechelin.ui.main.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowProfieService(val view: MypageActivityView) {
    fun showProfile(path:String,token:String){
        val mypageinterface = ApiClient.getRetrofit().create(MypageService::class.java)
        mypageinterface.getmypageInfo(path,token).enqueue(object :
            Callback<MypageResponse> {
            override fun onResponse(
                call: Call<MypageResponse>,
                response: Response<MypageResponse>
            ) {
                view.onGetProfileSuccess(response.body() as MypageResponse)
            }

            override fun onFailure(call: Call<MypageResponse>, t: Throwable) {
                view.onGetProfileFailure(t.message ?: "통신 오류")
            }

        })
    }
}