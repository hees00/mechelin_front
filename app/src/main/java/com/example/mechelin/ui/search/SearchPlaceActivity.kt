package com.example.mechelin.ui.save

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechelin.R
import com.example.mechelin.data.SearchStore
import com.example.mechelin.data.remote.KakaoapiInterface
import com.example.mechelin.data.remote.Place
import com.example.mechelin.data.remote.Researchkeyword
import com.example.mechelin.databinding.ActivitySearchPlaceBinding
import com.example.mechelin.ui.main.MainActivity
import com.example.mechelin.ui.main.WritingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchPlaceActivity : AppCompatActivity(){
    lateinit var binding : ActivitySearchPlaceBinding
    private var StoreDatas = ArrayList<SearchStore>();


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("createe","good")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivitySearchPlaceBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                StoreDatas.clear()
                //검색 버튼을 눌렀을 때 호출
                searchKeyword(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색창에서 글자가 변경이 일어날 때마다 호출
                return true
            }
        })
    }


    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "KakaoAK b897e9989fedf2db699cf5b819537e64"  // REST API 키
    }

    private fun searchKeyword(keyword: String) {
        val retrofit = Retrofit.Builder()   // Retrofit 구성
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(KakaoapiInterface::class.java)   // 통신 인터페이스를 객체로 생성
        val call = api.getSearchKeyword(API_KEY, keyword,1,10)   // 검색 조건 입력

        // API 서버에 요청
        call.enqueue(object: Callback<Researchkeyword> {
            override fun onResponse(
                call: Call<Researchkeyword>,
                response: Response<Researchkeyword>
            ) {
                // 통신 성공 (검색 결과는 response.body()에 담겨있음)
//                Log.d("Test", "Raw: ${response.raw()}")
//                Log.d("Test", "Body: ${response.body()}")
                showResult(response.body())
            }


            override fun onFailure(call: Call<Researchkeyword>, t: Throwable) {
                // 통신 실패
                Log.w("MainActivity", "통신 실패: ${t.message}")
            }
        })

    }

    fun showResult(result: Researchkeyword?){
        for(i in result!!.documents)
            StoreDatas.apply{
                add(SearchStore(i.place_name,i.address_name,i.phone,i.x,i.y))
            }
        Log.d("Test", "Body: ${result}")

        //어댑터 설정
        val searchRVAdapter = SearchRVAdapter(StoreDatas,itemClickedListener={
            getselectedStore(WritingFragment(),it)
        })
        //리사이클러 뷰에 연결
        binding.searchResultRv.adapter= searchRVAdapter
        //레이아웃 매니저 설정 (아이템 배치 어떻게?)
        binding.searchResultRv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }


    private fun getselectedStore(fragment: Fragment,store: SearchStore){
        val bundle = Bundle()
        bundle.putParcelable("store",store)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frm,fragment)
        transaction.commit()
    }

}
