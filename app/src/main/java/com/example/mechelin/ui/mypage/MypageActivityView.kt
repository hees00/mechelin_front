package com.example.mechelin.ui.mypage


interface MypageActivityView {
    fun onGetProfileSuccess(response: MypageResponse)

    fun onGetProfileFailure(message: String)
}