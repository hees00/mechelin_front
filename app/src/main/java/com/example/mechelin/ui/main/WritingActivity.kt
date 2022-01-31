package com.example.mechelin.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mechelin.R
import com.example.mechelin.data.SearchStore
import com.example.mechelin.data.remote.Store
import com.example.mechelin.databinding.ActivitySearchPlaceBinding
import com.example.mechelin.databinding.ActivityWritingBinding
import com.example.mechelin.databinding.FragmentHomeBinding
import com.example.mechelin.ui.save.SearchPlaceActivity

class WritingActivity: AppCompatActivity() {

    lateinit var binding: ActivityWritingBinding
    lateinit var store: Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityWritingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        makeTag()

        binding.writingCategorySelectTb.setOnClickListener {
            if (binding.writingCategorySelectTb.isChecked) {
                binding.writingCategoryLayout.visibility = View.VISIBLE
            } else {
                binding.writingCategoryLayout.visibility = View.GONE
            }
        }

//        if (arguments != null) {
//            Log.d("resultstore", arguments?.getParcelable<SearchStore>("store").toString())
//        }
//
//
//        binding.writingSearchView.setOnClickListener {
//            val intent = Intent(getActivity(), SearchPlaceActivity::class.java)
//            startActivity(intent)
//        }

    }
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentWritingBinding.inflate(inflater, container, false)
//
//        makeTag()
//
//        binding.writingCategorySelectTb.setOnClickListener {
//            if (binding.writingCategorySelectTb.isChecked) {
//                binding.writingCategoryLayout.visibility = View.VISIBLE
//            } else {
//                binding.writingCategoryLayout.visibility = View.GONE
//            }
//        }
//
////        if (arguments != null) {
////            Log.d("resultstore", arguments?.getParcelable<SearchStore>("store").toString())
////        }
////
////
////        binding.writingSearchView.setOnClickListener {
////            val intent = Intent(getActivity(), SearchPlaceActivity::class.java)
////            startActivity(intent)
////        }
//
//        return binding.root
//
//    }

    public fun categoryClick(view: View){
        Log.d("category",view.id.toString())
    }


    fun makeTag() {
        var beforeTag = "#행복 #하이 #진짜_진짜"
        var tag = beforeTag.split("#")
        var resultTag = ArrayList<String>()
        Log.d("HashTag", "tag"+tag.toString())
        for (i in 1..(tag.size-1)) {
            resultTag.add(tag[i].trim())
        }
        Log.d("HashTag", resultTag.toString())

    }
}

