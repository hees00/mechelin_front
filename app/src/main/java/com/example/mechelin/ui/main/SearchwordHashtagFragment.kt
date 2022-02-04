package com.example.mechelin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.mechelin.R
import com.example.mechelin.databinding.FragmentSearchwordHashtagBinding


class SearchwordHashtagFragment: Fragment() {

    lateinit var binding: FragmentSearchwordHashtagBinding
    private var foodDatas = ArrayList<Food>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchwordHashtagBinding.inflate(layoutInflater)

        val foodRVAdator = FoodRVAdator(foodDatas)

        binding.searchwordHashtagRecyclerview.adapter = foodRVAdator

        binding.searchwordHashtagRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

}