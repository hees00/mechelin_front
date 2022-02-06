package com.example.mechelin.ui.signup

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class SignupData(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phoneNumber") val phoneNumber: String
)
