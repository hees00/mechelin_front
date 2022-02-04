package com.example.mechelin.ui.main

import android.content.Intent
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getReview()
    }

    private fun getReview() {
        val reviewService = ApiClient.getRetrofit().create(ReviewInterface::class.java)

        reviewService.getReview().enqueue(object : Callback<ReviewResponse> {

            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                //Log.d("GETREVIEW/API-RESPONSE", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("GETREVIEW/API-RESPONSE", response.toString())
                    Log.d("GETREVIEW/API-DATA", resp.toString())


                    when (resp.code) {
                        1000 -> binding.homeRecentReviewRecyclerview.adapter = HomeReviewRVAdator(resp.result!!)
                        else -> Log.d(
                            "GETREVIEW/API-RESPONSE",
                            resp.code.toString() + ":" + resp.message
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Log.e("GETREVIEW/API-ERROR", t.message.toString())
            }

        })
    }

}
