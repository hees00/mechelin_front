package com.example.mechelin.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechelin.data.remote.SaveStoreResponse
import com.example.mechelin.databinding.ActivityDetailBinding
import com.example.mechelin.ui.main.ApiClient
import com.example.mechelin.ui.main.MainActivity
import com.example.mechelin.ui.main.getJwt
import com.example.mechelin.ui.main.getUserIdx
import retrofit2.*
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
//        val jwtToken = getJwt(this@DetailActivity)
//
//        val userIdx = shared.getInt("userIdx",-1)
        val storeIdx = intent.getIntExtra("storeIdx",1)

        binding.deleteContentBtn.setOnClickListener {
            deleteStore(storeIdx)
        }
        postService.getReviews(getJwt(this), getUserIdx(this),storeIdx,1,3).enqueue(object : Callback<PostResult> {
            override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                post = response.body()

                try {
                    binding.storInfoStoreName.setText(post?.result?.storeInformation?.storeName)
                    binding.storeInfoAvgPoint.setText("??????"+post?.result?.storeInformation?.averageStarRate.toString())
                    binding.storeInfoStoreAddress.setText(post?.result?.storeInformation?.storeAddress)
                    binding.storeInfoStoreNumber.setText(post?.result?.storeInformation?.storeTel)
                    binding.postRecyclerview.adapter = PostRVAdapter(post?.result?.reviewList!!,itemClickedListener={
                        delete(it)
                    })
                    binding.postRecyclerview.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                } catch (e: NullPointerException){
                    binding.storInfoStoreName.setText(post?.result?.storeInformation?.storeName)
                    binding.storeInfoAvgPoint.setText("??????"+post?.result?.storeInformation?.averageStarRate.toString())
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

    fun deleteStore(storeIdx:Int){
        ApiClient.settoken(getJwt(this))
        val deletestoreinterface = ApiClient.getRetrofit().create(PostService::class.java)
        deletestoreinterface.DeleteStore(getUserIdx(this),storeIdx).enqueue(object :
            Callback<deletestoreresponse> {
            override fun onResponse(call: Call<deletestoreresponse>, response: Response<deletestoreresponse>) {
                Log.d("DELETE-SUCCESS", "onresponse ?????????")
                Log.d("DELETE-SUCCESS", response.toString())
                if (response.code()==200){
                    val resp=response.body()
                    when(resp?.code){
                        1000 -> {Toast.makeText(getApplicationContext(), "????????? ?????????????????????", Toast.LENGTH_LONG)
                            moveToHome()
                        }
                        else -> Toast.makeText(getApplicationContext(), "?????? ??????????????????", Toast.LENGTH_LONG)
                    }
                }

            }

            override fun onFailure(call: Call<deletestoreresponse>, t: Throwable) {
                Log.d("API_FAILURE",t.message.toString() )
            }
        })
    }
    fun delete(reviewIdx:Int){
        ApiClient.settoken(getJwt(this))
        val deleteinterface = ApiClient.getRetrofit().create(PostService::class.java)
        deleteinterface.DeleteReview(getUserIdx(this),reviewIdx).enqueue(object :
            Callback<deleteresponse> {
            override fun onResponse(call: Call<deleteresponse>, response: Response<deleteresponse>) {
                Log.d("DELETE-SUCCESS", "onresponse ?????????")
                Log.d("DELETE-SUCCESS", response.toString())
                if (response.code()==200){
                    val resp=response.body()
                    when(resp?.code){
                        1000 -> {Toast.makeText(getApplicationContext(), "????????? ?????????????????????", Toast.LENGTH_LONG)
                                moveToHome()
                        }
                        else -> Toast.makeText(getApplicationContext(), "?????? ??????????????????", Toast.LENGTH_LONG)
                    }
                }

            }

            override fun onFailure(call: Call<deleteresponse>, t: Throwable) {
                Log.d("API_FAILURE",t.message.toString() )
            }
        })
    }

    fun moveToHome(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}