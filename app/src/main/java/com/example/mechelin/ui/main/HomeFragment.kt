package com.example.mechelin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechelin.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var reviewDatas = ArrayList<Review>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val reviewService = ApiClient.getRetrofit().create(ReviewInterface::class.java)

        reviewService.getReview().enqueue(object : Callback<ReviewResponse> {

            override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                //Log.d("GETREVIEW/API-RESPONSE", response.toString())

                if(response.isSuccessful && response.code() == 1000) {
                    val resp = response.body()!!
                    Log.d("GETREVIEW/API-RESPONSE", response.toString())

                    when(resp.code){
                        1000 -> reviewDatas = resp.result!!
                    }
                }

            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.d("GETREVIEW/API-ERROR", t.message.toString())
//                signUpView.onSignUpFailure(400, "네트워크 오류가 발생했습니다.")
            }

        })

        binding = FragmentHomeBinding.inflate(inflater, container, false)



        val reviewRVAdator = HomeReviewRVAdator(reviewDatas)

        binding.homeRecentReviewRecyclerview.adapter = reviewRVAdator

        binding.homeRecentReviewRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }
}
