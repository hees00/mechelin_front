package com.example.mechelin.ui.resign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.databinding.ActivityMypageBinding
import com.example.mechelin.databinding.ActivityResignBinding
import com.example.mechelin.ui.main.*
import com.example.mechelin.ui.signin.LoginmainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResignActivity: AppCompatActivity() {

    lateinit var binding: ActivityResignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resign)

        binding = ActivityResignBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.resignBtnCv.setOnClickListener {
            resign()
        }

    }

    private fun resign() {
        val resignService = ApiClient.getRetrofit().create(ResignInterface::class.java)

        resignService.resign(7,binding.resignPasswordEt.text.toString()).enqueue(object : Callback<ResignResponse> {

            override fun onResponse(
                call: Call<ResignResponse>,
                response: Response<ResignResponse>
            ) {
                Log.d("RESIGN",response.body().toString())
                when(response.body()!!.code){
                    1000 -> {
                        Toast.makeText(getApplicationContext(),"정상적으로 탈퇴되었습니다", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@ResignActivity,LoginmainActivity::class.java)
                        startActivity(intent)
                    }
                    2060 -> {
                        Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_LONG).show()
                    }
                }
                Log.d("RESIGN",response.body().toString())
            }

            override fun onFailure(call: Call<ResignResponse>, t: Throwable) {
                Log.d("RESIGN-FAILURE","회원 탈퇴 실패")
            }

        })
    }
}