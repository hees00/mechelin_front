package com.example.mechelin.ui.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.databinding.ActivityLoginBinding
import com.example.mechelin.ui.main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginLoginBtnDeactivate.setOnClickListener{
            val email = binding.loginInputEmail.getText().toString()
            val password = binding.loginInputPassword.getText().toString()
            val jwtToken = getSharedPreferences("jwt",0).getString("jwtToken","")

            var retrofit = Retrofit.Builder()
                .baseUrl("https://dev.mechelin.shop")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var loginService: LoginService = retrofit.create(LoginService::class.java)
            var login: LoginResult? = null
            var loginData: LoginData = LoginData(email, password)

            loginService.requestLogin(jwtToken!!,loginData).enqueue(object:Callback<LoginResult>{
                override fun onResponse(
                    call: Call<LoginResult>,
                    response: Response<LoginResult>
                ) {
                    login = response.body()
                    Log.d("Login","msg: "+login?.message)
                    Log.d("Login","code: "+login?.code)

                    if (login?.code == 1000) {
                        Toast.makeText(getApplicationContext(),"로그인에 성공했습니다", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@LoginActivity,MainActivity::class.java)

                        Log.d("Signin", "UserIdx: " + login?.result?.userIdx)
                        Log.d("Signin", "jwt: " + login?.result?.jwt)
                        var jwtToken = login?.result?.jwt
                        var userIdx = login?.result?.userIdx

                        saveJwt(this@LoginActivity, jwtToken.toString())
                        saveUserIdx(this@LoginActivity, userIdx!!.toInt())
                        saveCategoryIdx(this@LoginActivity, "8")

                        Log.d("Signin", "SharedUserIdx: " + getUserIdx(this@LoginActivity))
                        Log.d("Signin", "SharedUserjwt: " + getJwt(this@LoginActivity))

//                        val sharedUser = getSharedPreferences("user", 0)
//                        val editor = sharedUser.edit()
//                        editor.putString("jwtToken", jwtToken)
//                        editor.putInt("userIdx",userIdx!!)
//                        editor.apply()
//
//                        Log.d("Signin", "SharedUserIdx: " + sharedUser.getInt("userIdx",-1))
//                        Log.d("Signin", "SharedUserjwt: " + sharedUser.getString("jwtToken","null"))

                        startActivity(intent)
                    }else{
                        Toast.makeText(getApplicationContext(),login?.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                    Toast.makeText(getApplicationContext(),login?.message, Toast.LENGTH_LONG).show()
                }

            })

        }

    }

}