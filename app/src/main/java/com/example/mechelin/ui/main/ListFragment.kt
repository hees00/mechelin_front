package com.example.mechelin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechelin.databinding.FragmentListBinding
import com.example.mechelin.databinding.FragmentMapBinding
import com.example.mechelin.databinding.FragmentSearchwordHashtagBinding

class ListFragment: Fragment() {

    lateinit var binding: FragmentListBinding
    private var foodDatas = ArrayList<Food>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(layoutInflater)

        val foodRVAdator = FoodRVAdator(foodDatas)

        binding.listRecyclerview.adapter = foodRVAdator

        binding.listRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }
}