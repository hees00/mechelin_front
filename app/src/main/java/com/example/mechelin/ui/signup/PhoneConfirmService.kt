package com.example.mechelin.ui.signup

import android.util.Log
import com.example.mechelin.ui.main.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneConfirmService(val view: PhoneConfirmView) {
    fun requestconfirm(phoneNum: String){
        val request = ApiClient.getRetrofit().create(SignupService::class.java)
        request.authPhone(phoneNum).enqueue(object :
            Callback<PhoneResult> {

            override fun onFailure(call: Call<PhoneResult>, t: Throwable) {
                view.onconfirmrequestFailure(t.message ?: "통신 오류")
            }

            override fun onResponse(call: Call<PhoneResult>, response: Response<PhoneResult>) {
                Log.d("REQUEST-SUCCESS", "onresponse 들어옴")
                Log.d("REQUEST-SUCCESS", response.toString())
                when(response.code()){
                    200->view.onconfirmrequestSuccess(response.body() as PhoneResult)
                    else -> Log.d("REQUEST-FAIL", response.toString())
                }

            }
        })
    }

    fun checkNum(phoneNum: String,num:String){
        val request = ApiClient.getRetrofit().create(SignupService::class.java)
        request.confirmNum(phoneNum,num).enqueue(object :
            Callback<ConfrimNumResult> {

            override fun onFailure(call: Call<ConfrimNumResult>, t: Throwable) {
                view.onCheckNumFailure(t.message ?: "통신 오류")
            }

            override fun onResponse(call: Call<ConfrimNumResult>, response: Response<ConfrimNumResult>) {
                Log.d("REQUEST-SUCCESS", "인증확인 들어옴")
                Log.d("REQUEST-SUCCESS", response.toString())
                view.onCheckNumSuccess(response.body() as ConfrimNumResult)
            }
        })
    }
}