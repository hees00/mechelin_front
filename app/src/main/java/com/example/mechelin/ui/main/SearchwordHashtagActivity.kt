package com.example.mechelin.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mechelin.databinding.FragmentSearchwordHashtagBinding
import com.example.mechelin.ui.main.search.Hashtag
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchwordHashtagActivity: AppCompatActivity() {

    lateinit var binding: FragmentSearchwordHashtagBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchwordHashtagBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        getHashtagStore(1, 100)
    }


    private fun getHashtagStore(
        pageVal:Int,
        pageSizeVal:Int) {

        val HashtagstoreService = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val fullUrl = "/search/" + getUserIdx(this@SearchwordHashtagActivity) +"/hashtag/"

        HashtagstoreService.getHashtagStore(fullUrl, getJwt(this@SearchwordHashtagActivity), getTagIdx(this@SearchwordHashtagActivity), pageVal, pageSizeVal).enqueue(object : Callback<HashtagStoreResponse> {

            override fun onResponse(
                call: Call<HashtagStoreResponse>,
                response: Response<HashtagStoreResponse>
            ) {
                //Log.d("GETREVIEW/API-RESPONSE", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("GETSTORE/API-RESPONSE", response.toString())
                    Log.d("GETSTORE/API-DATA", resp.toString())


                    when (resp.code) {
                        1000 -> {
                            binding.searchwordHashtagRecyclerview.adapter = HashtagStoreRVAdaptor(resp.result)
                            binding.searchwordHashtagRecyclerview.layoutManager = GridLayoutManager(this@SearchwordHashtagActivity, 3)
                        }

                        else -> Log.d(
                            "GETSTORE/API-RESPONSE",
                            resp.code.toString() + ":" + resp.message
                        )
                    }
                }
            }

            override fun onFailure(call: Call<HashtagStoreResponse>, t: Throwable) {
                Log.e("GETSTORE/API-ERROR", t.message.toString())
            }

        })
    }

}