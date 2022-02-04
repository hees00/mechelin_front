package com.example.mechelin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R

import com.example.mechelin.databinding.ActivitySearchBinding


class SearchActivity: AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchFinishTv.setOnClickListener {
            finish()
        }
        buttonChange()
    }

    private fun buttonChange() {
        if (binding.searchSearchingEt.text.length > 0) {
            binding.searchMagnifierBlackIv.visibility = View.GONE
            binding.searchMagnifierRedIv.visibility = View.VISIBLE
        } else{
            binding.searchMagnifierBlackIv.visibility = View.VISIBLE
            binding.searchMagnifierRedIv.visibility = View.GONE
        }
    }

}