package com.example.mechelin.ui.main.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mechelin.databinding.ActivitySearchBinding
import com.example.mechelin.ui.main.ApiClient
import com.example.mechelin.ui.main.ApiInterface
import com.example.mechelin.ui.main.getJwt
import com.example.mechelin.ui.main.getUserIdx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity: AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchFinishTv.setOnClickListener {
            finish()
        }
        buttonChange()
    }

    override fun onStart() {
        super.onStart()
        binding.searchMagnifierBlackIv.setOnClickListener {
            getSearchStoreResult()
            getSearchHashtagResult()
        }

    }

    private fun buttonChange() {
        if (binding.searchSearchingEt.length() > 1) {
            binding.searchMagnifierBlackIv.visibility = View.GONE
            binding.searchMagnifierRedIv.visibility = View.VISIBLE
        } else{
            binding.searchMagnifierBlackIv.visibility = View.VISIBLE
            binding.searchMagnifierRedIv.visibility = View.GONE
        }
    }

    private fun getSearchStoreResult() {

        val apiService = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val fullUrl = "/search/" + getUserIdx(this@SearchActivity)

        apiService.getSearchStoreResult(fullUrl, getJwt(this), binding.searchSearchingEt.text.toString()).enqueue(object : Callback<SearchResponse> {

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                Log.d("GETREVIEW/API-RESPONSE", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("GETSTORE/API-RESPONSE", response.toString())
                    Log.d("GETSTORE/API-DATA", resp.toString())


                    when (resp.code) {
                        1000 -> {
                            if (resp.result.store == null){
                            }
                            else {
                                binding.searchStoreRecyclerview.adapter = SearchStoreRVAdaptor(resp.result.store)
                                binding.searchStoreRecyclerview.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                            }
                        }
                        else -> Log.d(
                            "GETSTORE/API-RESPONSE",
                            resp.code.toString() + ":" + resp.message
                        )
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("GETSTORE/API-ERROR", t.message.toString())
            }

        })
    }

    private fun getSearchHashtagResult() {

        val apiService = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val fullUrl = "/search/" + getUserIdx(this@SearchActivity)

        apiService.getSearchHashtagResult(fullUrl, getJwt(this), binding.searchSearchingEt.text.toString()).enqueue(object : Callback<SearchResponse> {

                override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                Log.d("GETHASHTAG/API-RESPONSE", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("GETHASHTAG/API-RESPONSE", response.toString())
                    Log.d("GETHASHTAG/API-DATA", resp.toString())


                    when (resp.code) {
                        1000 -> {
                            if (resp.result.hashtag == null) {
                            } else {
                                binding.searchHashtagRecyclerview.adapter = SearchHashtagRVAdaptor(resp.result.hashtag)
                                binding.searchHashtagRecyclerview.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                            }
                        }
                        else -> Log.d(
                            "GETHASHTAG/API-RESPONSE",
                            resp.code.toString() + ":" + resp.message
                        )
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("GETHASHTAG/API-ERROR", t.message.toString())
            }

        })
    }

}