package com.example.mechelin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.data.remote.Store
import com.example.mechelin.databinding.ActivityWritingBinding

class WritingActivity: AppCompatActivity(){

    lateinit var binding: ActivityWritingBinding

    var store: Store = Store("","","",0.0,0.0,1.0,"리뷰내용", makeTag(),1,"N")

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

        binding.writingCategoryKoreanfoodTb.setOnClickListener {
            onClick(it)
        }
        binding.writingCategoryWeternfoodTb.setOnClickListener {
            onClick(it)
        }
        binding.writingCategoryChineseTb.setOnClickListener {
            onClick(it)
        }
        binding.writingCategoryJapaneseTb.setOnClickListener {
            onClick(it)
        }
        binding.writingCategoryBasedfoodTb.setOnClickListener {
            onClick(it)
        }
        binding.writingCategoryPubTb.setOnClickListener {
            onClick(it)
        }
        binding.writingCategoryDessertTb.setOnClickListener {
            onClick(it)
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




    fun makeTag(): ArrayList<String> {
        var beforeTag = "#행복 #하이 #진짜_진짜"
        var tag = beforeTag.split("#")
        var resultTag = ArrayList<String>()
        Log.d("HashTag", "tag"+tag.toString())
        for (i in 1..(tag.size-1)) {
            resultTag.add(tag[i].trim())
        }
        Log.d("HashTag", resultTag.toString())
        return resultTag
    }

//
//    override fun onClick(view: View?) {
//        store.categoryIdx= Integer.parseInt(view!!.tag.toString())
//        binding.writingCategoryBasedfoodTb.isChecked=false
//        binding.writingCategoryKoreanfoodTb.isChecked=false
//        binding.writingCategoryChineseTb.isChecked=false
//        binding.writingCategoryJapaneseTb.isChecked=false
//        binding.writingCategoryWeternfoodTb.isChecked=false
//        binding.writingCategoryPubTb.isChecked=false
//        binding.writingCategoryDessertTb.isChecked=false
//        view!!.isSelected=true
//    }
    fun onClick(view: View?){
        store.categoryIdx= Integer.parseInt(view!!.tag.toString())
        binding.writingCategoryBasedfoodTb.isChecked=false
        binding.writingCategoryKoreanfoodTb.isChecked=false
        binding.writingCategoryChineseTb.isChecked=false
        binding.writingCategoryJapaneseTb.isChecked=false
        binding.writingCategoryWeternfoodTb.isChecked=false
        binding.writingCategoryPubTb.isChecked=false
        binding.writingCategoryDessertTb.isChecked=false
        when(view!!.tag.toString()){
            "1" -> binding.writingCategoryKoreanfoodTb.isChecked=true
            "2" ->  binding.writingCategoryWeternfoodTb.isChecked=true
            "3" -> binding.writingCategoryJapaneseTb.isChecked=true
            "4" -> binding.writingCategoryChineseTb.isChecked=true
            "5" -> binding.writingCategoryBasedfoodTb.isChecked=true
            "6" -> binding.writingCategoryPubTb.isChecked=true
            "7" -> binding.writingCategoryDessertTb.isChecked=true
        }
    }

}

