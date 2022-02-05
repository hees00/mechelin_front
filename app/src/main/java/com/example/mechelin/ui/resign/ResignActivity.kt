package com.example.mechelin.ui.resign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.databinding.ActivityMypageBinding
import com.example.mechelin.databinding.ActivityResignBinding

class ResignActivity: AppCompatActivity() {

    lateinit var binding: ActivityResignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resign)

        binding = ActivityResignBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        }
}