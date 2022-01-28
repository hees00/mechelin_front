package com.example.mechelin.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mechelin.data.SearchStore
import com.example.mechelin.data.remote.Store
import com.example.mechelin.databinding.FragmentHomeBinding
import com.example.mechelin.databinding.FragmentWritingBinding
import com.example.mechelin.ui.save.SearchPlaceActivity

class WritingFragment: Fragment() {

    lateinit var binding: FragmentWritingBinding
    lateinit var store: Store

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWritingBinding.inflate(inflater, container, false)

        makeTag()

        binding.writingCategorySelectTb.setOnClickListener {
            if (binding.writingCategorySelectTb.isChecked) {
                binding.writingCategoryLayout.visibility = View.VISIBLE
            } else {
                binding.writingCategoryLayout.visibility = View.GONE
            }
        }

        if (arguments != null) {
            Log.d("resultstore", arguments?.getParcelable<SearchStore>("store").toString())
        }


        binding.writingSearchView.setOnClickListener {
            val intent = Intent(getActivity(), SearchPlaceActivity::class.java)
            startActivity(intent)
        }

        return binding.root

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

