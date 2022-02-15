package com.example.mechelin.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechelin.databinding.ActivityDetailBinding
import com.example.mechelin.ui.main.MainActivity
import com.example.mechelin.ui.main.StoreRVAdaptor
import com.example.mechelin.ui.main.getJwt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev.mechelin.shop")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var postService: PostService = retrofit.create(PostService::class.java)
        var post: PostResult? = null

        val shared = getSharedPreferences("user",0)
//        val jwtToken = shared.getString("jwtToken","no-token")
        //val jwtToken = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NDQ3MDYwMzEsImV4cCI6MTY0NjE3NzI2MH0.jkzcRkhrtLb-q5RGa0xJALSZAYMQMNCUGca5X4krIhk"
        val jwtToken = getJwt(this@DetailActivity)

        val userIdx = shared.getInt("userIdx",-1)
        val storeIdx = intent.getIntExtra("storeIdx",1)

        postService.getReviews(jwtToken!!, userIdx,storeIdx,1,3).enqueue(object : Callback<PostResult> {
            override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                post = response.body()

                try {
                    binding.storInfoStoreName.setText(post?.result?.storeInformation?.storeName)
                    binding.storeInfoAvgPoint.setText("평균"+post?.result?.storeInformation?.averageStarRate.toString())
                    binding.storeInfoStoreAddress.setText(post?.result?.storeInformation?.storeAddress)
                    binding.storeInfoStoreNumber.setText(post?.result?.storeInformation?.storeTel)
                    binding.postRecyclerview.adapter = PostRVAdapter(post?.result?.reviewList!!)
                    binding.postRecyclerview.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                } catch (e: NullPointerException){
                    binding.storInfoStoreName.setText(post?.result?.storeInformation?.storeName)
                    binding.storeInfoAvgPoint.setText("평균"+post?.result?.storeInformation?.averageStarRate.toString())
                    binding.storeInfoStoreAddress.setText(post?.result?.storeInformation?.storeAddress)
                    binding.storeInfoStoreNumber.setText(post?.result?.storeInformation?.storeTel)
                }


                if(post?.code == 1000){
                    Log.d("Detail", post?.message.toString()+" : "+post?.code)
                    Log.d("Detail", "review count : " + post?.result?.reviewList)

                }else{
                    Log.d("Detail", post?.message.toString()+" : "+post?.code)
                }
            }

            override fun onFailure(call: Call<PostResult>, t: Throwable) {
                Log.d("Detail", t.message.toString())
            }
        })

    }
}