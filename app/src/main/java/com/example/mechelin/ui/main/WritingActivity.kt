package com.example.mechelin.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.data.remote.Store
import com.example.mechelin.databinding.ActivityWritingBinding
import android.content.Intent
import android.view.LayoutInflater
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.mechelin.databinding.ActivityPopupScoreBinding
import com.example.mechelin.ui.save.SearchPlaceActivity


class WritingActivity: AppCompatActivity(){

    lateinit var binding: ActivityWritingBinding

    var store: Store = Store("","","",0.0,0.0,0.0,"리뷰내용", makeTag(),1,"N")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityWritingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        makeTag()

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
                store.x=myData.getDoubleExtra("x",0.0)
                store.y=myData.getDoubleExtra("y",0.0)
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

    }


    //해시태그 만들기
    fun makeTag(): ArrayList<String> {
        var beforeTag = "#행복 #하이 #진짜_진짜"
        var tag = beforeTag.split("#")
        var resultTag = ArrayList<String>()
        Log.d("HashTag", "tag"+tag.toString())
        for (i in 1..(tag.size-1)) {
            resultTag.add(tag[i].trim())
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


}

