package com.example.mechelin.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun saveJwt(context: Context, jwt: String){
    val spf = context.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("jwt", jwt)
    editor.apply()
}

fun getJwt(context: Context): String {
    val spf = context.getSharedPreferences("jwt", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("jwt", "")!!
}

fun saveUserIdx(context: Context, userIdx: Int){
    val spf = context.getSharedPreferences("userIdx", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putInt("userIdx", userIdx)
    editor.apply()
}

fun getUserIdx(context: Context): Int {
    val spf = context.getSharedPreferences("userIdx", AppCompatActivity.MODE_PRIVATE)

    return spf.getInt("userIdx", 0)
}

fun saveCategoryIdx(context: Context, categoryIdx: String){
    val spf = context.getSharedPreferences("categoryIdx", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("categoryIdx", categoryIdx)
    editor.apply()
}

fun getCategoryIdx(context: Context): String {
    val spf = context.getSharedPreferences("categoryIdx", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("categoryIdx", "")!!
}

fun saveTagIdx(context: Context, tagIdx: Int){
    val spf = context.getSharedPreferences("tagIdx", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putInt("tagIdx", tagIdx)
    editor.apply()
}

fun getTagIdx(context: Context): Int {
    val spf = context.getSharedPreferences("tagIdx", AppCompatActivity.MODE_PRIVATE)
    return spf.getInt("tagIdx", 0)
}

fun saveHashtag(context: Context, hashtag: String){
    val spf = context.getSharedPreferences("hashtag", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("hashtag", hashtag)
    editor.apply()
}

fun getHashtag(context: Context): String {
    val spf = context.getSharedPreferences("hashtag", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("hashtag", "")!!
}
