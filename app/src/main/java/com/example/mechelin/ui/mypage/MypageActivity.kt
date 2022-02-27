package com.example.mechelin.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.mechelin.R
import com.example.mechelin.databinding.ActivityMypageBinding
import com.example.mechelin.databinding.ActivityWritingBinding
import com.example.mechelin.ui.main.MainActivity
import com.example.mechelin.ui.main.getJwt
import com.example.mechelin.ui.main.getUserIdx
import com.example.mechelin.ui.resign.ResignActivity
import com.example.mechelin.ui.savestore.WritingActivity
import com.example.mechelin.ui.signin.LoginmainActivity


class MypageActivity:AppCompatActivity(),MypageActivityView{

    lateinit var binding: ActivityMypageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        binding = ActivityMypageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mypageResignTv.setOnClickListener {
            val intent = Intent(this, ResignActivity::class.java)
            startActivity(intent)
        }
        binding.mypageLogoutTv.setOnClickListener {
            logout()
        }

        Log.d("GETUSERIDX",getUserIdx(this).toString())
        val path = "/users/" + getUserIdx(this)
        ShowProfieService(this).showProfile(path, getJwt(this))

    }

    override fun onGetProfileSuccess(response: MypageResponse) {
        Log.d("GETPROFILE",response.toString())
        binding.mypageNicknameTv.text=response.result.nickName
        binding.mypageEmailTv.text=response.result.email
        binding.mypageTotalReviewContentTv.text=response.result.reviewCnt.toString()
        binding.mypageSavePlaceContentTv.text=response.result.storeCnt.toString()
    }

    override fun onGetProfileFailure(message: String) {
        Log.d("GETPROFILE",message)
    }

    fun logout(){
        com.example.mechelin.ui.main.logout(this)
        Log.d("LOGOUT", "JWT:"+getJwt(this)?.toString()+ "userid:"+getUserIdx(this)?.toString())
        val intent = Intent(this, LoginmainActivity::class.java)
        startActivity(intent)
    }

}
