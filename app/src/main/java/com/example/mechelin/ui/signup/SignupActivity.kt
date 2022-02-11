package com.example.mechelin.ui.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mechelin.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtnDeactivate.setOnClickListener {
            val nickName = binding.signupInputNickname.getText().toString()
            val phoneNumber = binding.signupInputPhone.getText().toString()
            val password = binding.signupInputPassword.getText().toString()
            val email = binding.signupInputEmail.getText().toString()

            var retrofit = Retrofit.Builder()
                .baseUrl("https://dev.mechelin.shop")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var signupService: SignupService = retrofit.create(SignupService::class.java)
            var signup: SignupResult? = null
            var signupData: SignupData = SignupData(nickName, email, password, phoneNumber)

            signupService.requestSignup(signupData).enqueue(object : Callback<SignupResult> {
                override fun onResponse(
                    call: Call<SignupResult>,
                    response: Response<SignupResult>
                ) {
                    signup = response.body()
                    Log.d("Signup", signup.toString())
                    Log.d("Signup", "msg: " + signup?.message)
                    Log.d("Signup", "code: " + signup?.code)
                    Log.d("Signup", "jwt: " + signup?.result?.jwt)
                    var jwtToken = signup?.result?.jwt
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다", Toast.LENGTH_LONG)
                        .show()

                    val sharedPreference = getSharedPreferences("jwt", 0)
                    val editor = sharedPreference.edit()
                    editor.putString("jwtToken", jwtToken)
                    editor.apply()
                }

                override fun onFailure(call: Call<SignupResult>, t: Throwable) {
                    Log.d("Signup", "msg: " + signup?.message)
                    Log.d("Signup", "code: " + signup?.code)
                    Toast.makeText(getApplicationContext(), "회원가입이 실패했습니다", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }
}