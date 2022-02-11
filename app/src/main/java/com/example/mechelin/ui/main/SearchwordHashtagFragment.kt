package com.example.mechelin.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mechelin.databinding.FragmentSearchwordHashtagBinding


class SearchwordHashtagFragment: Fragment() {

    lateinit var binding: FragmentSearchwordHashtagBinding
    private var foodDatas = ArrayList<StoreResult>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchwordHashtagBinding.inflate(layoutInflater)

        val foodRVAdator = StoreRVAdaptor(foodDatas)

        binding.searchwordHashtagRecyclerview.adapter = foodRVAdator

        binding.searchwordHashtagRecyclerview.layoutManager = GridLayoutManager(context, 3)

        return binding.root
    }

}