package com.example.mechelin.ui.savestore

import android.R.attr
import android.R.attr.*
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.R
import com.example.mechelin.data.remote.Store
import com.example.mechelin.databinding.ActivityWritingBinding
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mechelin.data.remote.SaveStoreResponse
import com.example.mechelin.ui.main.ApiClient
import com.example.mechelin.ui.search.SearchPlaceActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.*
import java.net.URL
import java.io.*
import okhttp3.MultipartBody

import java.io.IOException

import java.io.InputStream
import java.security.AccessController.getContext
import java.io.FileNotFoundException

import java.io.FileOutputStream

import java.io.File
import java.lang.Exception
import android.os.Environment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.database.Cursor
import android.graphics.Bitmap
import com.example.mechelin.ui.main.getJwt
import com.example.mechelin.ui.main.getUserIdx
import okhttp3.RequestBody





class WritingActivity: AppCompatActivity() ,WritingActivityView {


    lateinit var binding: ActivityWritingBinding


    var store: Store = Store(15, 0, "N", "", "", 0.0, 0.0, "", arrayListOf<String>(), 0.0, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        store.userIdx= getUserIdx(this)

        binding = ActivityWritingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //???????????? ??????
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

        //?????? ?????? ????????????
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val myData: Intent? = result.data
                    store.storeName = myData!!.getStringExtra("storename").toString()
                    store.address = myData.getStringExtra("storeaddress").toString()
                    store.tel = myData.getStringExtra("phone").toString()
                    store.x = myData.getStringExtra("x")!!.toDouble()
                    store.y = myData.getStringExtra("y")!!.toDouble()
                    Log.d("storeinfo", store.toString())

                    binding.writingSearchTv.text = store.storeName
                    binding.writingAddressEt.setText(store.address)
                    binding.writingPhoneEt.setText(store.tel)

                }
            }

        //?????? ?????? ???????????????
        binding.writingSearchView.setOnClickListener {
            var intent = Intent(this, SearchPlaceActivity::class.java)
            resultLauncher.launch(intent)
        }

        //?????? ??????
        binding.writingScoreIv.setOnClickListener{
            getStarscore()
        }
        binding.writingScoreTv.setOnClickListener{
            getStarscore()
        }

        //????????????
        binding.writingTagEt.doAfterTextChanged {
            store.tagName = makeTag(it.toString())
        }

        //???????????? ?????????
        binding.writingReviewContentEt.doAfterTextChanged {
            store.contents = it.toString()
        }

        //?????? ?????? ??????
        binding.writingDeliveryTb.setOnClickListener {
            if (binding.writingDeliveryTb.isChecked) {
                store.deliveryService = "Y"
            } else {
                store.deliveryService = "N"
            }
        }

        //?????? ?????? ????????? ???
        binding.writingCompleteButtonTv.setOnClickListener {

            val images = arrayListOf<MultipartBody.Part?>()
            val jwt= getJwt(this)

            for (i in 0..(pathList.size - 1)) {
                val uploadbitmap = Bitmap.createScaledBitmap(pathList[i], 300, 300, true)
                val stream = ByteArrayOutputStream()
                uploadbitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray = stream.toByteArray()
                val sendimage = byteArray.toRequestBody("image/png".toMediaTypeOrNull())
                val multibody: MultipartBody.Part =
                    MultipartBody.Part.createFormData("imageFile", "image.png", sendimage)
                images.add(multibody)
            }


            val sendstore = store.toString().toRequestBody("application/json".toMediaTypeOrNull())
            if (images.size == 0) {
                Log.d("NO-PHOTO",images.toString())
                Toast.makeText(
                    getApplicationContext(),
                    "????????? ??????????????????",
                    Toast.LENGTH_LONG)
//                WritingActivityService(this).tryWriting(store,null,jwt)
            } else {
                WritingActivityService(this).tryWriting(store, images,jwt)
            }
        }
        //?????? ?????????
        binding.writingUploadPictureCv.setOnClickListener {
            requeststorage()
        }

    }


    //???????????? ?????????
    fun makeTag(beforeTag: String): ArrayList<String> {
        var tag = beforeTag.split("#")
        var resultTag = ArrayList<String>()
        Log.d("HashTag", "tag" + tag.toString())
        for (i in 1..(tag.size - 1)) {
            resultTag.add('#' + tag[i].trim())
        }
        Log.d("HashTag", resultTag.toString())
        return resultTag
    }

    //?????? ????????????
    fun getStarscore() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_popup_score, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.findViewById<com.hedgehog.ratingbar.RatingBar>(R.id.popup_score_ratingbar)
            .setOnRatingChangeListener {
                Log.d("starscore", it.toString())
                store.starRate = it.toDouble()
                binding.writingScoreTv.visibility=View.GONE
                when (store.starRate) {
                    5.0 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_5_xxhdpi)
                    4.5 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_4andhalf_xxhdpi)
                    4.0 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_4_xxhdpi)
                    3.5 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_3andhalf_xxhdpi)
                    3.0 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_3_xxhdpi)
                    2.5 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_2andhalf_xxhdpi)
                    2.0 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_2_xxhdpi)
                    1.5 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_1andhalf_xxhdpi)
                    1.0 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_1_xxhdpi)
                    0.5 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_drag_half_xxhdpi)
                    0.0 -> binding.writingScoreIv.setImageResource(R.drawable.writingpage_rating_gray_xxhdpi)
                }
            }
        mDialogView.findViewById<TextView>(R.id.popup_score_btn_confirm_tv).setOnClickListener {
            mAlertDialog.dismiss()
        }
        mDialogView.findViewById<TextView>(R.id.popup_score_btn_cancel_tv).setOnClickListener {
            store.starRate = 0.0
            mAlertDialog.dismiss()
        }

    }

    //??????????????????
    fun showpictures() {
        val selectpicturesRVAdapter = SelectPicturesRVAdapter(imageList)
        //??????????????? ?????? ??????
        binding.writingUploadRv.adapter = selectpicturesRVAdapter
        //???????????? ????????? ?????? (????????? ?????? ??????????)
        binding.writingUploadRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }


    //???????????? ??????
    fun onClick(view: View?) {
        store.categoryIdx = Integer.parseInt(view!!.tag.toString())
        binding.writingCategoryBasedfoodTb.isChecked = false
        binding.writingCategoryKoreanfoodTb.isChecked = false
        binding.writingCategoryChineseTb.isChecked = false
        binding.writingCategoryJapaneseTb.isChecked = false
        binding.writingCategoryWeternfoodTb.isChecked = false
        binding.writingCategoryPubTb.isChecked = false
        binding.writingCategoryDessertTb.isChecked = false
        when (view!!.tag.toString()) {
            "1" -> binding.writingCategoryKoreanfoodTb.isChecked = true
            "2" -> binding.writingCategoryWeternfoodTb.isChecked = true
            "3" -> binding.writingCategoryJapaneseTb.isChecked = true
            "4" -> binding.writingCategoryChineseTb.isChecked = true
            "5" -> binding.writingCategoryBasedfoodTb.isChecked = true
            "6" -> binding.writingCategoryPubTb.isChecked = true
            "7" -> binding.writingCategoryDessertTb.isChecked = true
        }
    }

    //?????? ????????????
    val imageList = arrayListOf<Uri>()
    val pathList = arrayListOf<Bitmap>()

    // ?????? Activity??? ????????? ?????? ???????????? ??????
    val getphotoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.data?.clipData != null) { // ?????? ????????? ????????? ??????
                    val count = result.data!!.clipData!!.itemCount
                    if (count > 3) {
                        Toast.makeText(getApplicationContext(),"????????? ?????? 3????????? ?????? ???????????????." , Toast.LENGTH_LONG).show()
                        return@registerForActivityResult
                    }
                    imageList.clear()
                    pathList.clear()
                    for (i in 0 until count) {
                        val dataUri = result.data?.data
                        val imageUri = result.data?.clipData!!.getItemAt(i).uri
                        val add = imageList.add(imageUri)

                        val inputStream = imageUri.let {
                            contentResolver.openInputStream(
                                it
                            )
                        }
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        inputStream!!.close()
                        pathList.add(bitmap)
                        Log.d("BITMAP",pathList.toString())
                    }
                    showpictures()
                    binding.writingCountPicturesTv.text= count.toString() + "/3"

                } else { // ?????? ??????
                    result.data?.data?.let { uri ->
                        imageList.clear()
                        pathList.clear()
                        imageList.add(uri)
                        val inputStream = uri.let {
                            contentResolver.openInputStream(
                                it
                            )
                        }
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        inputStream!!.close()
                        pathList.add(bitmap)
                        binding.writingUploadRv.visibility = View.VISIBLE
                        binding.writingCountPicturesTv.text= "1/3"

                        showpictures()
                    }
                }
            }



    fun getpictures() {
        val intent = Intent()
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        getphotoLauncher.launch(intent)
    }


    private fun requeststorage() {
        // ?????? ???????????? ????????????
        val cameraPermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // ????????? ????????? ????????? ????????? ??????
            getpictures()

        } else {
            // ????????? ????????? ???????????? ????????? ??????
            requestPermission()
        }
    }

    // ?????? ??????
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            99
        )
    }

    // ?????? ??????
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            99 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getpictures()
                } else {
                    Log.d("REQUEST_DENIED", "??????")
                }
            }
        }
    }

    override fun onPostWritingSuccess(response: SaveStoreResponse) {

        Log.d("writing-resp", response.code.toString())
        Log.d("writing-resp", response.toString())
        when (response.code) {
            1000 -> {
                    Log.d("SAVESUCCESS", response.result.toString())
                    finish()
            }
            2040, 2041, 2042, 2043, 2044 -> {
                AlertDialog.Builder(this@WritingActivity)
                    .setMessage(response.message)
                    .create()
                    .show()
            }
            400, 500, 2001, 2002, 2003, 2010, 4000 -> {
                AlertDialog.Builder(this@WritingActivity)
                    .setMessage("?????? ?????? ??? ?????? ????????? ?????????????????????.")
                    .create()
                    .show()
            }
        }
    }

    override fun onPostWritingFailure(message: String) {
        Log.e("SAVESTORE/API-ERROR", message)
    }

}



