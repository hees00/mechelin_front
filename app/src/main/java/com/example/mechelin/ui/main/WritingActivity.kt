package com.example.mechelin.ui.main

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.data.remote.Store
import com.example.mechelin.databinding.ActivityWritingBinding
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.widget.doAfterTextChanged
import com.example.mechelin.BuildConfig
import com.example.mechelin.data.remote.SaveStoreResponse
import com.example.mechelin.data.remote.SaveStoreService
import com.example.mechelin.databinding.ActivityPopupScoreBinding
import com.example.mechelin.ui.search.SearchPlaceActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File
import androidx.core.app.ActivityCompat.startActivityForResult





class WritingActivity: AppCompatActivity(){

    lateinit var binding: ActivityWritingBinding

    var store: Store = Store(0,"","","",0.0,0.0,0.0,"리뷰내용", arrayListOf<String>(),1,"N")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityWritingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //카테고리 선택
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

        //식당 정보 받아오기
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val myData: Intent? = result.data
                store.storeName = myData!!.getStringExtra("storename").toString()
                store.address=myData.getStringExtra("storeaddress").toString()
                store.tel=myData.getStringExtra("phone").toString()
                store.x=myData.getStringExtra("x")!!.toDouble()
                store.y=myData.getStringExtra("y")!!.toDouble()
                Log.d("storeinfo",store.toString())

                binding.writingSearchTv.text=store.storeName
                binding.writingAddressEt.setText(store.address)
                binding.writingPhoneEt.setText(store.tel)

            }
        }

        //식당 정보 받으러가기
        binding.writingSearchView.setOnClickListener {
            var intent = Intent(this,SearchPlaceActivity::class.java)
            resultLauncher.launch(intent)
        }

        //별점 받기
        binding.writingScoreIv.setOnClickListener {
            getStarscore()
        }

        //해시태그
        binding.writingTagEt.doAfterTextChanged {
            store.tagName=makeTag(it.toString())
        }

        //리뷰내용 가져옴
        binding.writingReviewContentEt.doAfterTextChanged {
            store.contents=it.toString()
        }

        //배달 가능 여부
        binding.writingDeliveryTb.setOnClickListener {
            if(binding.writingDeliveryTb.isChecked){
                store.deliveryService="Y"
            }
            else{
                store.deliveryService="N"
            }
        }

        //완료 버튼 눌렀을 때
        binding.writingCompleteButtonTv.setOnClickListener {
//            savePlace()
            Log.d("saveplace",store.toString())
        }

        //사진 업로드
        binding.writingUploadPictureCv.setOnClickListener {
//            uploadPhoto()
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT

            getphotoLauncher.launch(intent)
        }


    }


    //해시태그 만들기
    fun makeTag(beforeTag: String): ArrayList<String> {
        var tag = beforeTag.split("#")
        var resultTag = ArrayList<String>()
        Log.d("HashTag", "tag"+tag.toString())
        for (i in 1..(tag.size-1)) {
            resultTag.add('#'+tag[i].trim())
        }
        Log.d("HashTag", resultTag.toString())
        return resultTag
    }

    //별점 받아오기
    fun getStarscore(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_popup_score, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val  mAlertDialog=mBuilder.show()

        mDialogView.findViewById<com.hedgehog.ratingbar.RatingBar>(R.id.popup_score_ratingbar).setOnRatingChangeListener{
            Log.d("starscore",it.toString())
            store.starRate= it.toDouble()
        }
        mDialogView.findViewById<TextView>(R.id.popup_score_btn_confirm_tv).setOnClickListener {
            mAlertDialog.dismiss()
        }
        mDialogView.findViewById<TextView>(R.id.popup_score_btn_cancel_tv).setOnClickListener {
            store.starRate=0.0
            mAlertDialog.dismiss()
        }

    }


    //카테고리 클릭
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

    //사진 가져오기
    val imageList=arrayListOf<Uri>()
    // 위의 Activity를 실행한 이후 이벤트를 정의
    val getphotoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK ) {
            if (result.data?.clipData != null) { // 사진 여러개 선택한 경우
                val count = result.data!!.clipData!!.itemCount
                if (count > 10) {
//                        showToast(getString(R.string.max_image_select_msg))
                    return@registerForActivityResult
                }
                imageList.clear()
                for (i in 0 until count) {
                    val imageUri = result.data?.clipData!!.getItemAt(i).uri
                    imageList.add(imageUri)
                }
                binding.writingUploadIv.visibility= View.VISIBLE
                binding.writingUploadIv.setImageURI(result.data?.clipData!!.getItemAt(0).uri)
                binding.writingUploadIv.setImageURI(imageList[0])

//                    if (count > 1) {
//                        binding.tvImageCount.visibility = View.VISIBLE
//                        binding.tvImageCount.text = "외 " + (count - 1) + "장"
//                    } else {
//                        binding.tvImageCount.visibility = View.GONE
//                    }
            } else { // 단일 선택
                result.data?.data?.let { uri ->
                    imageList.clear()
                    imageList.add(uri)
                    binding.writingUploadIv.visibility= View.VISIBLE
                    binding.writingUploadIv.setImageURI(uri)
                    Log.d("onephoto",uri.toString())
//                        binding.tvImageCount.visibility = View.GONE
                }
            }
        }
    }

    //식당 저장
    fun savePlace(){
        //레트로핏 객체 만듦
        val retrofit = Retrofit.Builder().baseUrl("https://dev.mechelin.shop").build()
        val saveStoreService = retrofit.create(SaveStoreService::class.java)

        saveStoreService.saveStore(store).enqueue(object : Callback<SaveStoreResponse>{
            override fun onResponse(call: Call<SaveStoreResponse>, response: Response<SaveStoreResponse>) {
                val resp = response.body()!!

                Log.d("writing-resp",resp.message)
                when(resp.code){
                    1000 -> finish()
                    2040,2041,2042,2043,2044 -> {
                        AlertDialog.Builder(this@WritingActivity)
                            .setMessage(resp.message)
                            .create()
                            .show()
                    }
                    400,500,2001,2002,2003,2010,4000 -> {
                        AlertDialog.Builder(this@WritingActivity)
                            .setMessage("식당 저장 및 리뷰 작성을 실패하였습니다.")
                            .create()
                            .show()
                    }
                }
            }


            override fun onFailure(call: Call<SaveStoreResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }




}

