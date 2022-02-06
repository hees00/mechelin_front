package com.example.mechelin.ui.signin

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
