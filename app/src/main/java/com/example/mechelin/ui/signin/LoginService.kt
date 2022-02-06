package com.example.mechelin.ui.signin

import com.example.mechelin.ui.signup.SignupData
import com.example.mechelin.ui.signup.SignupResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {
    @POST("/users/sign-in/")
    fun requestLogin(
        @Header("jwt") jwtToken: String,
        @Body loginData: LoginData
    ) : Call<LoginResult>
}