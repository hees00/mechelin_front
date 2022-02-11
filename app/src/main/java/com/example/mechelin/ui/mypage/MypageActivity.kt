package com.example.mechelin.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.databinding.ActivityMypageBinding
import com.example.mechelin.databinding.ActivityWritingBinding
import com.example.mechelin.ui.resign.ResignActivity
import com.example.mechelin.ui.savestore.WritingActivity


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

        ShowProfieService(this).showProfile()

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

}
