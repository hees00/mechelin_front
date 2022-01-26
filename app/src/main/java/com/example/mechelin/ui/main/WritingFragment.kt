package com.example.mechelin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mechelin.databinding.FragmentHomeBinding
import com.example.mechelin.databinding.FragmentWritingBinding

class WritingFragment: Fragment() {

    lateinit var binding: FragmentWritingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWritingBinding.inflate(inflater, container, false)

        binding.writingCategorySelectTb.setOnClickListener {
            if (binding.writingCategorySelectTb.isChecked) {
                binding.writingCategoryLayout.visibility = View.VISIBLE
            } else {
                binding.writingCategoryLayout.visibility = View.GONE
            }
        }

//        makeTag()
        return binding.root

    }

//    fun makeTag() {
//        var beforeTag = "#행복 #하이 #진짜_진짜"
//        var tag = beforeTag.split("#")
//        Log.d("HashTag",tag.toString())
//        for (i in 1 .. tag.size){
//            tag.get(1)=tag[i].trim()
//        }

    }

