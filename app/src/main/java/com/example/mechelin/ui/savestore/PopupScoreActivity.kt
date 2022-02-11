package com.example.mechelin.ui.savestore

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.databinding.ActivityPopupScoreBinding
import com.example.mechelin.databinding.ActivityWritingBinding

class PopupScoreActivity(): AppCompatActivity(){
    lateinit var binding: ActivityPopupScoreBinding



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_popup_score)

            binding = ActivityPopupScoreBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)


            // 배경을 투명하게함
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // 추가 버튼 클릭 시 onAddButtonClicked 호출 후 종료
            binding.popupScoreBtnConfirmTv.setOnClickListener {
            }

            // 취소 버튼 클릭 시 onCancelButtonClicked 호출 후 종료
            binding.popupScoreBtnConfirmTv.setOnClickListener {
            }
        }
    }
