package com.example.mechelin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechelin.R
import com.example.mechelin.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var reviewDatas = ArrayList<Review>();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //데이터 리스트 생성
        reviewDatas.apply {
            add(Review("도우룸", R.drawable.starscore_4_3, "4.5/5", "ddd"))
            add(Review("도우룸", R.drawable.starscore_4_3, "4.5/5", "ddd"))
            add(Review("도우룸", R.drawable.starscore_4_3, "4.5/5", "ddd"))
            add(Review("도우룸", R.drawable.starscore_4_3, "4.5/5", "ddd"))
            add(Review("도우룸", R.drawable.starscore_4_3, "4.5/5", "ddd"))
        }


        binding.homeRecentReviewRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }
}