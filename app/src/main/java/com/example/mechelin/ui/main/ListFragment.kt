package com.example.mechelin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mechelin.R
import com.example.mechelin.databinding.FragmentListBinding
import com.example.mechelin.ui.main.review.HomeReviewRVAdator
import com.example.mechelin.ui.main.review.ReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment: Fragment() {

    lateinit var binding: FragmentListBinding
    private var starRatingYN: String = "Y"
    private var deliveryServiceYN: String = "N"
    private lateinit var storeresult: StoreResult

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(layoutInflater)

//        when(storeresult.storeIdx){
//            1 -> binding.listCategoryIv.setImageResource(R.drawable.korean_food_writing_page_choose_category)
//            2 -> binding.listCategoryIv.setImageResource(R.drawable.western_food_chosen_category_and_list_top_bar)
//            3 -> binding.listCategoryIv.setImageResource(R.drawable.japanese_food_chosen_category_and_list_top_bar)
//            4 -> binding.listCategoryIv.setImageResource(R.drawable.chinese_food_chosen_category_and_list_top_bar)
//            5 -> binding.listCategoryIv.setImageResource(R.drawable.flour_based_food_chosen_category_and_list_top_bar)
//            6 -> binding.listCategoryIv.setImageResource(R.drawable.pub_chosen_category_and_list_top_bar)
//            7 -> binding.listCategoryIv.setImageResource(R.drawable.dessert_chosen_category_and_list_top_bar)
//            8 -> binding.listCategoryIv.setImageResource(R.drawable.all_home)
//        }

        binding.listNewGrayTv.setOnClickListener {
            binding.listNewGrayTv.visibility = View.GONE
            binding.listNewRedTv.visibility = View.VISIBLE
            binding.listNewEclipseGrayIv.visibility = View.GONE
            binding.listNewEclipseRedIv.visibility = View.VISIBLE
            binding.listStarrateRedTv.visibility = View.GONE
            binding.listStarrateGrayTv.visibility = View.VISIBLE
            binding.listStarrateEclipseRedIv.visibility = View.GONE
            binding.listStarrateEclipseGrayIv.visibility = View.VISIBLE
            starRatingYN = "N"
            getStore(starRatingYN, deliveryServiceYN, 1, 100)
        }

        binding.listStarrateGrayTv.setOnClickListener {
            binding.listNewGrayTv.visibility = View.VISIBLE
            binding.listNewRedTv.visibility = View.GONE
            binding.listNewEclipseGrayIv.visibility = View.VISIBLE
            binding.listNewEclipseRedIv.visibility = View.GONE
            binding.listStarrateRedTv.visibility = View.VISIBLE
            binding.listStarrateGrayTv.visibility = View.GONE
            binding.listStarrateEclipseRedIv.visibility = View.VISIBLE
            binding.listStarrateEclipseGrayIv.visibility = View.GONE
            starRatingYN = "Y"
            getStore(starRatingYN, deliveryServiceYN, 1, 100)
        }

//        binding.listStarrateGrayTv.setOnClickListener {
//            binding.listStarrateGrayTv.visibility = View.GONE
//            binding.listStarrateRedTv.visibility = View.VISIBLE
//            binding.listStarrateEclipseGrayIv.visibility = View.GONE
//            binding.listStarrateEclipseRedIv.visibility = View.VISIBLE
//            starRatingYN
//        }
//
//        binding.listStarrateRedTv.setOnClickListener {
//            binding.listStarrateGrayTv.visibility = View.VISIBLE
//            binding.listStarrateRedTv.visibility = View.GONE
//            binding.listStarrateEclipseGrayIv.visibility = View.VISIBLE
//            binding.listStarrateEclipseRedIv.visibility = View.GONE
//        }

        binding.listDeliveryGrayTv.setOnClickListener {
            binding.listDeliveryGrayTv.visibility = View.GONE
            binding.listDeliveryRedTv.visibility = View.VISIBLE
            binding.listDeliveryGrayIv.visibility = View.GONE
            binding.listDeliveryRedIv.visibility = View.VISIBLE
            deliveryServiceYN = "Y"
            getStore(starRatingYN, deliveryServiceYN, 1, 100)
        }

        binding.listDeliveryRedIv.setOnClickListener {
            binding.listDeliveryGrayTv.visibility = View.VISIBLE
            binding.listDeliveryRedTv.visibility = View.GONE
            binding.listDeliveryGrayIv.visibility = View.VISIBLE
            binding.listDeliveryRedIv.visibility = View.GONE
            deliveryServiceYN = "N"
            getStore(starRatingYN, deliveryServiceYN, 1, 100)
        }

//        val foodRVAdator = StoreRVAdaptor(storeDatas)
//        binding.listRecyclerview.adapter = foodRVAdator

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getStore(starRatingYN, deliveryServiceYN, 1, 100)
    }

    private fun getStore(
        starRatingVal:String,
        deliveryServiceVal:String,
        pageVal:Int,
        pageSizeVal:Int) {
        val storeService = ApiClient.getRetrofit().create(ApiInterface::class.java)

        storeService.getStore(starRatingVal, deliveryServiceVal, pageVal, pageSizeVal).enqueue(object : Callback<StoreResponse> {

            override fun onResponse(
                call: Call<StoreResponse>,
                response: Response<StoreResponse>
            ) {
                //Log.d("GETREVIEW/API-RESPONSE", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("GETSTORE/API-RESPONSE", response.toString())
                    Log.d("GETSTORE/API-DATA", resp.toString())


                    when (resp.code) {
                        1000 -> {binding.listRecyclerview.adapter = StoreRVAdaptor(resp.result)
                            binding.listRecyclerview.layoutManager = GridLayoutManager(context, 3)
                        }

                        else -> Log.d(
                            "GETSTORE/API-RESPONSE",
                            resp.code.toString() + ":" + resp.message
                        )
                    }
                }
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                Log.e("GETSTORE/API-ERROR", t.message.toString())
            }

        })
    }
}