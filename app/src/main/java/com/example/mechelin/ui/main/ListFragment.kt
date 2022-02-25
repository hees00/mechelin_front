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
    //private var categoryIdx = getCategoryIdx(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var categoryIdx = getCategoryIdx(requireContext())

        binding = FragmentListBinding.inflate(layoutInflater)


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
            getStore(categoryIdx, starRatingYN, deliveryServiceYN, 1, 100)
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
            getStore(categoryIdx, starRatingYN, deliveryServiceYN, 1, 100)
        }


        binding.listDeliveryGrayTv.setOnClickListener {
            binding.listDeliveryGrayTv.visibility = View.GONE
            binding.listDeliveryRedTv.visibility = View.VISIBLE
            binding.listDeliveryGrayIv.visibility = View.GONE
            binding.listDeliveryRedIv.visibility = View.VISIBLE
            deliveryServiceYN = "Y"
            getStore(categoryIdx, starRatingYN, deliveryServiceYN, 1, 100)
        }

        binding.listDeliveryRedIv.setOnClickListener {
            binding.listDeliveryGrayTv.visibility = View.VISIBLE
            binding.listDeliveryRedTv.visibility = View.GONE
            binding.listDeliveryGrayIv.visibility = View.VISIBLE
            binding.listDeliveryRedIv.visibility = View.GONE
            deliveryServiceYN = "N"
            getStore(categoryIdx, starRatingYN, deliveryServiceYN, 1, 100)
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        var categoryIdx = getCategoryIdx(requireContext())
        getStore(categoryIdx, starRatingYN, deliveryServiceYN, 1, 100)
    }

    private fun getStore(
        categoryIdx: String,
        starRatingVal:String,
        deliveryServiceVal:String,
        pageVal:Int,
        pageSizeVal:Int) {

        val storeService = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val fullUrl = "/stores/" + getUserIdx(requireContext()) +"/" + categoryIdx

        storeService.getStore(fullUrl, getJwt(requireContext()), starRatingVal, deliveryServiceVal, pageVal, pageSizeVal).enqueue(object : Callback<StoreResponse> {

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
                        1000 -> {
                            binding.listRecyclerview.adapter = StoreRVAdaptor(resp.result)
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