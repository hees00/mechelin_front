package com.example.mechelin.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.View
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
            if(binding.signupInputPassword.text.toString() != binding.signupInputPasswordCheck.text.toString()){
                binding.signupPasswordNotsame.visibility= View.VISIBLE
            }else {
                val nickName = binding.signupInputNickname.getText().toString()
                val phoneNumber = makephoneNumber(
                    binding.signupInputPhone1.text.toString(),
                    binding.signupInputPhone2.text.toString(),
                    binding.signupInputPhone3.text.toString()
                )
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
                        Log.d("Signup", "userIdx: " + signup?.result?.userIdx)

                        var jwtToken = signup?.result?.jwt
                        var userIdx = signup?.result?.userIdx

                        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다", Toast.LENGTH_LONG)
                            .show()

                        val sharedUser = getSharedPreferences("User", 0)
                        val editor = sharedUser.edit()

                        editor.putString("jwtToken", jwtToken)
                        editor.putInt("userIdx", userIdx!!)
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
    fun makephoneNumber(p1:String,p2:String,p3:String):String{
        return p1+p2+p3
    }
}